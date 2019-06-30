package by.it.maniuk.jd02_02;

import java.util.ArrayList;
import java.util.List;

public class Cashier implements Runnable {

    final static Object monitor = new Object();

    int number;
    private String name;

    Cashier(int number) {
        name = "Cashier №" + number + " ";
        this.number = number;
    }

    @Override
    public void run() {
        while (! Dispatcher.planComplete()) {
            Buyer buyer = null;
                if (Queue.needCashier()) {
                 // Queue.dellCashiers(this);
                  Queue.extractCashier();
                   buyer = Queue.extractBuyer();
                }

            if (buyer != null) {
                System.out.println(this + "started service of " + buyer);
                int timeout = Util.rnd(2000, 5000);
                Util.sleep(timeout);

               // print(buyer);

                System.out.println(this + "stopped service of " + buyer);
                synchronized (buyer){
                    buyer.notify();
                }

            } else {
                synchronized (monitor){
                    try {
                        Queue.addCashier(this);
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void print(Buyer buyer) {
        List<String> list = buyer.getGoods();
        StringBuilder sb = new StringBuilder();
        StringBuilder probel = null;
        for (int i = 0; i < number*15 ; i++) {
          probel = sb.append(" ");
        }
        for (String good : list) {
            sb.append(probel).append(good).append(" ").append(Goods.getCost().get(good)).append("\n");
        }
        Double cost = 0d;
        for (String good :list) {
            cost += Goods.getCost().get(good);
        }
        cost = Math.floor(cost * 100) / 100;
        sb.append("summa = ").append(cost);
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        return name;
    }
}
