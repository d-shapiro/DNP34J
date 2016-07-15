package br.org.scadabr.dnp34j.master.session;

import br.org.scadabr.dnp34j.logging.DNPLogger;

public class WatchDog extends Thread {
	private DNPUser user;
	private boolean STOP;

	public WatchDog(DNPUser user) {
		this.user = user;
	}

	public void run() {
		DNPLogger.LOGGER.info("[Watchdog] Started!");
		STOP = false;
		while (!STOP) {
			if (!user.getAppRcv().isAlive())
				DNPLogger.LOGGER.info("appRcv is dead!");
			if (!user.getAppSnd().isAlive())
				DNPLogger.LOGGER.info("appSnd is dead!");
			if (!user.getLnkRcv().isAlive())
				DNPLogger.LOGGER.info("lnkRcv is dead!");
			if (!user.getLnkSnd().isAlive())
				DNPLogger.LOGGER.info("lnkSnd is dead!");

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				DNPLogger.LOGGER.error("", e);
			}
		}
	}

	public void setSTOP(boolean sTOP) {
		STOP = sTOP;
	}

	public boolean isSTOP() {
		return STOP;
	}
}
