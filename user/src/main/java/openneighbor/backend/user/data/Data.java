package openneighbor.backend.user.data;

import java.util.Properties;

public class Data {

  private final String hostname;
  private final Properties properties;

  public Data(String hostname, Properties properties) {
    this.hostname = hostname;
    this.properties = properties;
  }

  public String getHostname() {
    return hostname;
  }

  public Properties getProperties() {
    return properties;
  }

  @Override
  public boolean equals(Object host) {
    if (host instanceof Data) {
      return hostname.equals(((Data) host).getHostname());
    }
    return false;
  }
}
