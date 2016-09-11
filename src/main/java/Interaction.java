/**
 * Created by yuqia_000 on 2016/9/11.
 */
public class Interaction {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("wrong number of arguments");
            System.out.println("expected: nodeName mailboxName cookie");
            return;
        }

        Interaction ex = new Interaction(args[0], args[1], args[2]);

        System.out.println("JavaNode is now working!");
        ex.doInteraction();

    }

    private OtpNode node;
    private OtpMbox mbox;

    public Interaction (String nodeName, String mboxName, String cookie)
            throws Exception{
        super();
        node = new OtpNode(nodeName,cookie);
        mbox = node.createMbox(mboxName);
    }

    private void doInteraction() {
        while (true) {
            try {
                OtpErlangObject msg = mbox.receive();
                System.out.println("Receive a message from erlang already!");
                OtpErlangTuple t = (OtpErlangTuple) msg;
                OtpErlangPid from = (OtpErlangPid) t.elementAt(0);

                String OstMsg;
                OtpErlangString repmsg;


                OtpErlangTuple outMsg =
                        new OtpErlangTuple(new OtpErlangObject[]{mbox.self(),repmsg});
                mbox.send(from,outMsg);
            }catch (Exception e) {
                System.out.println("caught error: "+e);
            }
        }

    }
}
