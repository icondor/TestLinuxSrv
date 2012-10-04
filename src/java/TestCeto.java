import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;
import net.schmizz.sshj.userauth.UserAuthException;

import java.io.IOException;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: icondor
 * Date: 2/23/12
 * Time: 11:38 PM
 * To change this template use File | Settings | File Templates.
 */
/*


import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** This examples demonstrates how a remote command can be executed. */
public class TestCeto {

	private static final String HOST="";
	private static final String PORT="1";
	private static final String USER="";
	private static final String PWD="";
	

	
    public static void main(String... args)
            throws IOException {
        final SSHClient ssh = init();
        executeTest(ssh);
    }

	public static SSHClient init() throws IOException {
		final SSHClient ssh = new SSHClient();
        ssh.addHostKeyVerifier(
                new HostKeyVerifier() {
                    public boolean verify(String arg0, int arg1, PublicKey arg2) {
                        return true;  // don't bother verifying
                    }
                }
        );
        ssh.connect(HOST, Integer.parseInt(PORT));
		return ssh;
	}

	public static void executeTest(final SSHClient ssh) throws UserAuthException,
			TransportException, ConnectionException, IOException {
		try {
            ssh.authPassword(USER, PWD);
             boolean exp=false;
             do   {
            final Session session = ssh.startSession();
            try {
                final Session.Command cmd = session.exec("whoami");
                System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
                cmd.join(15, TimeUnit.SECONDS);
                // System.out.println("\n** exit status: " + cmd.getExitStatus());
                System.out.println("----");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } catch (ConnectionException ex) {
                System.out.println("ddddd");
                ex.printStackTrace();
            } finally {
                System.out.println("ooooo");
                session.close();
            }
             }
            while(exp==false);
        } finally {
            System.out.println("aaaaa");
            ssh.disconnect();
        }
	}
}
