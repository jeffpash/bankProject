/**
 * Copyright : Iheb <br/>
 *
 * @version 1.0<br/>
 */
package com.banque.dao;

import java.util.List;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;

/**
 * Dao de base.
 *
 * @param <T>
 *            entite geree
 */
public interface IDAO<T extends IEntity> {

	/**
	 * Insert un element.
	 *
	 * @param uneEntite
	 *            l'element a inserer.
	 * @return l'element insere.
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract T insert(T uneEntite) throws ExceptionDao;

	/**
	 * Met a jour un element.
	 *
	 * @param uneEntite
	 *            l'element a mettre a jour.
	 * @return l'element mis a jour.
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract T update(T uneEntite) throws ExceptionDao;

	/**
	 * Supprime un element.
	 *
	 * @param pUneEntite
	 *            l'element a supprimer.
	 * @return true si il a bien ete supprime
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract boolean delete(T pUneEntite) throws ExceptionDao;

	/**
	 * Selectionne l'element ayant comme valeur de clef primaire le parametre.
	 *
	 * @param pUneClef
	 *            la valeur de la clef primaire
	 *
	 * @return l'element trouve ou null si aucun
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract T select(Object pUneClef) throws ExceptionDao;

	/**
	 * Selectionne tous les elements qui correspondent aux criteres.
	 *
	 * @param pAWhere
	 *            une clause where (sans 'where')
	 * @param pAnOrderBy
	 *            une clause orderby (sans 'orderby')
	 *
	 * @return la liste des elements trouves ou une liste vide si aucun
	 * @throws ExceptionDao
	 *             si une erreur survient
	 */
	public abstract List<T> selectAll(String pAWhere, String pAnOrderBy)
			throws ExceptionDao;

}