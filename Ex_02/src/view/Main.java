package view;

import java.util.concurrent.Semaphore;

import controller.ThreadJogo;

public class Main {

	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore mutex = new Semaphore(permissao);
		
		for (int id = 0; id < 5; id++) {
			Thread threadJogo = new ThreadJogo(id + 1, mutex);
			threadJogo.start();
		}
	}

}
