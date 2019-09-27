/**
 * Copyright : Iheb <br/>
 *
 * @version 1.0<br/>
 */
package com.banque.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.banque.dao.ICompteDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ICompteEntity;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.EntityIntrouvableException;
import com.banque.service.ex.ErreurTechniqueException;

/**
 * Gestion des comptes.
 */
@Service("compteService")
public class CompteService extends AbstractService implements ICompteService {

	private ICompteDAO compteDao;

	/**
	 * Constructeur de l'objet.
	 */
	public CompteService() {
		super();
	}

	/**
	 * Recupere la propriete <i>compteDao</i>.
	 *
	 * @return the compteDao la valeur de la propriete.
	 */
	public ICompteDAO getCompteDao() {
		return this.compteDao;
	}

	/**
	 * Fixe la propriete <i>compteDao</i>.
	 *
	 * @param pCompteDao
	 *            la nouvelle valeur pour la propriete compteDao.
	 */
	@Autowired
	public void setCompteDao(@Qualifier("compteDAO") ICompteDAO pCompteDao) {
		this.compteDao = pCompteDao;
	}

	@Override
	public ICompteEntity select(Integer unUtilisateurId, Integer unCompteId)
			throws EntityIntrouvableException, AucunDroitException,
			NullPointerException, ErreurTechniqueException {
		if (unUtilisateurId == null) {
			throw new NullPointerException("utilisateurId");
		}
		if (unCompteId == null) {
			throw new NullPointerException("compteId");
		}
		ICompteEntity resultat = null;
		try {
			resultat = this.getCompteDao().select(unCompteId);
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}
		if (resultat == null) {
			throw new EntityIntrouvableException();
		}

		if (!unUtilisateurId.equals(resultat.getUtilisateurId())) {
			throw new AucunDroitException();
		}

		return resultat;
	}

	/**
	 * Recupere tous les comptes d'un utilisateur.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @return les comptes trouves, une liste vide si aucun
	 * @throws EntityIntrouvableException
	 *             si le compte n'existe pas
	 * @throws AucunDroitException
	 *             si l'utilisateur n'a pas le droit d'acceder a ce compte
	 * @throws NullPointerException
	 *             si un des parametres est nul
	 * @throws ErreurTechniqueException
	 *             si un probleme survient
	 */
	@Override
	public List<ICompteEntity> selectAll(Integer unUtilisateurId)
			throws EntityIntrouvableException, AucunDroitException,
			NullPointerException, ErreurTechniqueException {
		if (unUtilisateurId == null) {
			throw new NullPointerException("utilisateurId");
		}
		List<ICompteEntity> resultat = new ArrayList<>();
		try {
			resultat = this.getCompteDao().selectAll(
					"entity.utilisateurId=" + unUtilisateurId,
					"entity.libelle ASC");
		} catch (ExceptionDao e) {
			throw new ErreurTechniqueException(e);
		}

		return resultat;
	}
}