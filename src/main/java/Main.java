/**
 * Copyright : Iheb <br/>
 *
 * @version 1.0<br/>
 */
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;

/**
 * Exemple.
 */
public final class Main {
	/** Main log. */
	private final static Log LOG = LogFactory.getLog(Main.class);

	/**
	 * Exemple de fonctionnement.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		if (Main.LOG.isInfoEnabled()) {
			Main.LOG.info("-- Debut -- ");
		}
		ClassPathXmlApplicationContext appContext = null;
		try {
			appContext = new ClassPathXmlApplicationContext(new String[] {
					"data-context.xml", "aop-context.xml", "tx-context.xml" });

			IAuthentificationService serviceAuth = (IAuthentificationService) appContext
					.getBean("authentificationService");
			IUtilisateurEntity utilisateur = serviceAuth.authentifier("dj",
					"dj");
			if (Main.LOG.isInfoEnabled()) {
				Main.LOG.info("Bonjour " + utilisateur.getNom() + " "
						+ utilisateur.getPrenom());
			}
			ICompteService serviceCpt = (ICompteService) appContext
					.getBean("compteService");
			List<ICompteEntity> listeCpts = serviceCpt.selectAll(utilisateur
					.getId());
			if (Main.LOG.isInfoEnabled()) {
				Main.LOG.info("Vous avez " + listeCpts.size() + " comptes");
			}
			// On prend deux id de comptes pour faire un virement
			Integer[] deuxId = new Integer[2];
			Iterator<ICompteEntity> iter = listeCpts.iterator();
			int id = 0;
			while (iter.hasNext() && (id < deuxId.length)) {
				ICompteEntity compteEntity = iter.next();
				deuxId[id] = compteEntity.getId();
				id++;
			}

			// On Effectue un virement entre deux comptes, via le service des
			// operations
			IOperationService serviceOp = (IOperationService) appContext
					.getBean("operationService");
			serviceOp.faireVirement(utilisateur.getId(), deuxId[0], deuxId[0],
					Double.valueOf(5));
			if (Main.LOG.isInfoEnabled()) {
				Main.LOG.info("Votre virement s'est bien effectué");
			}
		} catch (Exception e) {
			Main.LOG.fatal("Erreur", e);
			System.exit(-1);
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}

		if (Main.LOG.isInfoEnabled()) {
			Main.LOG.info("-- Fin -- ");
		}
		System.exit(0);
	}
}
