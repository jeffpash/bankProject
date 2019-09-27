/**
 * Copyright : Iheb <br/>
 *
 * @version 1.0<br/>
 */
package com.banque.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.UtilisateurEntity;

/**
 * Gestion des utilisateurs.
 */
@Repository("utilisateurDAO")
public class UtilisateurDAO extends AbstractDAO<IUtilisateurEntity> implements
		IUtilisateurDAO {

	/**
	 * Constructeur de l'objet.
	 */
	public UtilisateurDAO() {
		super();
	}

	@Override
	protected String getEntityClassName() {
		return UtilisateurEntity.class.getName();
	}

	@Override
	protected String getPkName() {
		return "id";
	}

	@Override
	public IUtilisateurEntity selectLogin(String pLogin) throws ExceptionDao {
		List<IUtilisateurEntity> allLogin = super.selectAll("entity.login='"
				+ pLogin + "'", null);
		if ((allLogin == null) || allLogin.isEmpty()) {
			return null;
		}
		// On retourne le premier
		return allLogin.iterator().next();
	}

}