package controller;

import java.util.concurrent.Semaphore;

public class ThreadJogo extends Thread {

	private int id;
	private Semaphore mutex;

	public ThreadJogo(int id, Semaphore mutex) {
		this.id = id;
		this.mutex = mutex;
	}

	public void run() {
		cozimentoPrato();
	}

	private void cozimentoPrato() {
		if (id % 2 == 0) {
			System.out.println("Sopa de cebola #" + id + " começou a ser preparada");
			int tempoSopa = (int) ((Math.random() * 301) + 500);
			int tempoDecorrido = 0;
			while (tempoDecorrido < tempoSopa) {
				double porcentagemSopa = (tempoDecorrido / (double) tempoSopa) * 100;
				System.out.println("Sopa #" + id + " " + porcentagemSopa + "% cozida");
				tempoDecorrido += 100;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Sopa #" + id + " 100% cozida");
			semaforo();
		} else {
			System.out.println("Lasanha Bolonhesa #" + id + " começou a ser preparada");
			int tempoLasanha = (int) ((Math.random() * 601) + 600);
			int tempoDecorrido = 0;
			while (tempoDecorrido < tempoLasanha) {
				double porcentagemLasanha = (tempoDecorrido / (double) tempoLasanha) * 100;
				System.out.println("Lasanha Bolonhesa #" + id + " " + porcentagemLasanha + "% cozida");
				tempoDecorrido += 100;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Lasanha Bolonhesa #" + id + " 100% cozida");
			semaforo();
		}
	}

	private void semaforo() {
		try {
			mutex.acquire();
			entregaPrato();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			mutex.release();
		}
	}

	private void entregaPrato() {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id % 2 == 0) {
			System.out.println("Sopa #" + id + " entregue");
		}else {
			System.out.println("Lasanha Bolonhesa #" + id + " entregue");
		}
	}

}
