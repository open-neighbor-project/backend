package openneighbor.backend.notification.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
//import com.google.api.client.extensions.jetty.auth.oauth2;

import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import openneighbor.backend.notification.model.NotificationModel;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class EmailNotificationService implements INotificationService<Message> {

    private Gmail service;
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private NetHttpTransport HTTP_TRANSPORT;
    private final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_LABELS);
    private final String TOKENS_DIRECTORY_PATH = "tokens";

    public EmailNotificationService() throws FileNotFoundException, IOException, GeneralSecurityException {
        HTTP_TRANSPORT =  GoogleNetHttpTransport.newTrustedTransport();

        InputStream in = getClass().getResourceAsStream("token.json");
        if (in == null) {
            throw new FileNotFoundException("Resource not found: token.json");
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credentials = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
                .setApplicationName("Open Neighbor")
                .build();

        // Print the labels in the user's account.
        String user = "me";
        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        if (labels.isEmpty()) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Labels:");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }
    }

    public Message notify(NotificationModel notification) throws MessagingException, IOException {
        MimeMessage emailContents = this.createEmail(notification.getRecipient(), notification.getTitle(), notification.getMessage());
        return sendMessage(service, emailContents);
    }

    /**
     * Create a MimeMessage from our email address to the recipient
     *
     * @param recipient email address of the recipient
     * @param subject email subject
     * @param body email body
     * @return the MimeMessage to be used to send email
     *
     * @throws MessagingException
     */
    private MimeMessage createEmail(String recipient, String subject, String body) throws MessagingException  {
        Properties properties = new Properties();
        Session session = Session.getDefaultInstance(properties, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress("TODO: GET AN EMAIL FOR THE APP"));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
        email.setSubject(body);

        return email;
    }

    /**
     * Create a Message from the encoded email
     *
     * @param emailContents The email to be encoded and sent in the message
     * @return a message containing the email, base46url encoded
     *
     * @throws IOException
     * @throws MessagingException
     */
    private Message createMessage(MimeMessage emailContents) throws IOException, MessagingException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContents.writeTo(buffer);

        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);

        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    /**
     * Send an email from the user's mailbox to its recipient.
     *
     * @param service Authorized Gmail API instance.
     * @param emailContent Email to be sent.
     * @return The sent message
     * @throws MessagingException
     * @throws IOException
     */
    public Message sendMessage(Gmail service, MimeMessage emailContent) throws MessagingException, IOException {
        Message message = this.createMessage(emailContent);
        message = service.users().messages().send("me", message).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
        return message;
    }
}
