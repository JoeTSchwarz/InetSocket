# InetSocket
Socket and ServerSocket for Internet with HostID instead of domain name

Instead of a registered domain name (e.g., via DNS), a name agreed upon with a server can be transmitted via broadcast. The server with the broadcast ID then responds with its IPv6 address and the corresponding port. This allows the client to access it legally and programmatically via this IP address and port. The package is called joesocket and contains three Java APIs:

- InetSocket: an extension of Socket,
- InetServerSocket: an extension of ServerSocket,
- IPv6: This API determines the valid dynamic IPv6 address that your internet service provider assigns to your router (and your local computer).

Further application details can be found in the files TestSocket.java and TestServer.java. The joesocket.jar file is available for download.
