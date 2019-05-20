package sd_ef;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

/**
 *
 * @author adriano
 */
public class MBCP {
    private int[] VT = {0, 0, 0, 0, 0, 0};
    private int K;
    private int TK;
    private CopyOnWriteArrayList<Mensaje> createdMessage = new CopyOnWriteArrayList<Mensaje>();
    private CopyOnWriteArrayList<Mensaje> receivedMessage = new CopyOnWriteArrayList<Mensaje>();
    private CopyOnWriteArrayList<Mensaje> waitingMessage = new CopyOnWriteArrayList<Mensaje>();
    private CopyOnWriteArrayList<Integer> CI = new CopyOnWriteArrayList<Integer>();

    public MBCP() {
        
    }
    public void evalMessage(Mensaje m, int i) {
        Interfaz o = new Interfaz();
        if (evalVT(m) && evalHM(m)) {
            this.VT[m.getNumeroDeProceso() - 1]++;
            o.printVT();
            receivedMessage.add(m);
            o.printDelivery(this.receivedMessage);
            processVerify(m, CI);
            reaper(CI, m);
            o.printCI(this.CI);
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
        o.printWaiting(waitingMessage);
    }
    
    
    public boolean evalVT(Mensaje m) {
        boolean eval = true;
        if (this.VT[m.getNumeroDeProceso() - 1] + 1 == m.getNumeroDeMensaje()) {
            eval = true;
//            System.out.println(m.getNumeroDeProceso());
        } else {
            eval = false;
        }
        return eval;
        
    } 

    public boolean evalHM(Mensaje m) {
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
        return eval;
    }
    
    public void processVerify(Mensaje m, CopyOnWriteArrayList<Integer> ci) {
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
    
    public void reaper(CopyOnWriteArrayList<Integer> ci, Mensaje m) {
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
