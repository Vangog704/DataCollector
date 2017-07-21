package Observers;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/responseChanegeSubscribe")
public class ResponseEndpoint {

	@OnOpen
	public void handleOnOpen(Session usersession, EndpointConfig ec) {
			ResponseObserver.getInstanse().addSession(usersession);
	}
	
	@OnClose
	public void handleOnClose(Session usersession)
	{
			ResponseObserver.getInstanse().removeSession(usersession);
	}	
	
}
