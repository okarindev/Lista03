package controller;

import java.util.concurrent.Semaphore;

public class ThreadCalBD extends Thread {

	private int id;
	private Semaphore mutex;

	public ThreadCalBD(int id, Semaphore mutex) {
		this.id = id;
		this.mutex = mutex;
	}

	public void run() {
		int esperaCal = 0;
		int esperaTransacao = 0;
		if (id % 3 == 1) {
			esperaCal = (int) ((Math.random() * 801) + 200);
			esperaTransacao = 100;
			for (int i = 0; i < 2; i++) {
				calculando(esperaCal);
				semaforo(esperaTransacao);
			}
		} else {
			if (id % 3 == 2) {
				esperaCal = (int) ((Math.random() * 1001) + 500);
				esperaTransacao = 1500;
				for (int i = 0; i < 2; i++) {
					calculando(esperaCal);
					semaforo(esperaTransacao);
				}
			} else {
				if (id % 3 == 0) {
					esperaCal = (int) ((Math.random() * 1001) + 1000);
					esperaTransacao = 1500;
					for (int i = 0; i < 2; i++) {
						calculando(esperaCal);
						semaforo(esperaTransacao);
					}
				}
			}
		}
	}

	private void transacaoBD(int espera) {
		System.out.println("# " + id + " esta realizando transacao de BD");
		try {
			sleep(espera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void calculando(int espera) {
		System.out.println("# " + id + " esta realizando calculos");
		try {
			sleep(espera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void semaforo(int esperaTransacao) {
		System.out.println("#" + id + " entrando em secao critica");
		try {
			mutex.acquire();
			transacaoBD(esperaTransacao);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
			// FIM DA SECAO CRITICA
		}
	}
}
