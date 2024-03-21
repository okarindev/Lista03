package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCalBD;

public class Main {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore mutex = new Semaphore(permissao);
		for(int id = 0; id < 21; id++ ) {
			Thread threadCalBD = new ThreadCalBD(id + 1, mutex);
			threadCalBD.start();
		}
	}

}
