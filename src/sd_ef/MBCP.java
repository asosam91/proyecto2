package sd_ef;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author adriano
 */
public class MBCP extends Interfaz{
    private int[] VT = {0, 0, 0, 0, 0, 0};
    private int K;
    private int TK;
    private ArrayList<Mensaje> createdMessage = new ArrayList<Mensaje>();
    private ArrayList<Mensaje> receivedMessage = new ArrayList<Mensaje>();
    private ArrayList<Mensaje> waitingMessage = new ArrayList<Mensaje>();
    private ArrayList<Integer> CI = new ArrayList<Integer>();

    public MBCP() {
    }
    public void evalMessage(Mensaje m, int i) {
        if (evalVT(m) && evalHM(m)) {
            this.VT[m.getNumeroDeProceso() - 1]++;
            printVT();
            receivedMessage.add(m);
            printDelivery(receivedMessage);
            processVerify(m, CI);
            reaper(CI, m);
            printCI(this.CI);
            if (waitingMessage.stream().filter(a -> a.getNumeroDeProceso() == m.getNumeroDeProceso() && a.getNumeroDeMensaje() == m.getNumeroDeMensaje()).count() == 1) {
                waitingMessage.remove(i);
            }
            if (!waitingMessage.isEmpty()) {
                evalBufferMessage();
            }
        } else {
            if (waitingMessage.stream().filter(a -> a.getNumeroDeProceso() == m.getNumeroDeProceso() && a.getNumeroDeMensaje() == m.getNumeroDeMensaje()).count() == 0) {
                waitingMessage.add(m);
            }
        }
        printWaiting(waitingMessage);
    }
    
    public boolean evalVT(Mensaje m) {
        int n = 0;
        boolean eval = true;
        if (this.VT[m.getNumeroDeProceso() - 1] + 1 == m.getNumeroDeMensaje()) {
            eval = true;
        } else {
            eval = false;
        }
        if (eval == true) {
            n = 1;
        } else {
            n = 0;
        }
        return eval;
    } 

    public boolean evalHM(Mensaje m) {
        int n = 0;
        boolean eval = true;
        int var = m.getHM().size();
        if (!m.getHM().isEmpty()) {
            for (int i = 0; i < var; i = i + 2) {
                if (m.getHM().get(i + 1) <= this.VT[m.getHM().get(i) - 1]) {
                    eval = true;
                } else {
                    eval = false;
                }
            }
        } else {
            eval = true;
        }
        if (eval == true) {
            n = 1;
        } else {
            n = 0;
        }
        return eval;
    }
    
    public void processVerify(Mensaje m, ArrayList<Integer> ci) {
        if (!ci.isEmpty()) {
            for (int i = 0; i < ci.size(); i += 2) {
                if (m.getNumeroDeProceso() == ci.get(i)) {
                       ci.remove(i+1);
                       ci.remove(i);
                }
            }
        }
        ci.add(m.getNumeroDeProceso());
        ci.add(m.getNumeroDeMensaje());
    }
    
    public void reaper(ArrayList<Integer> ci, Mensaje m) {
        if (!ci.isEmpty()) {
            for (int i = 0; i < m.getHM().size(); i += 2) {
                for (int j = 0; j < ci.size(); j += 2) {
                    if (ci.get(j) == m.getHM().get(i) && ci.get(j + 1) == m.getHM().get(i + 1)) {
                        ci.set(j, -1);
                        ci.set(j + 1, -1);
                    }
                }
            }
            for (int i = 0; i < ci.size(); i++) {
                if (ci.get(i) == -1) {
                    ci.remove(i);
                    i--;
                }
            }
        }
    }
    
    public void evalBufferMessage() {
        String var = "";
        for (int i = 0; i < waitingMessage.size(); i++) {
            var = waitingMessage.get(i).toString();
            Mensaje m1 = new Mensaje(var);
            evalMessage(m1, i);
        }
    }
}
