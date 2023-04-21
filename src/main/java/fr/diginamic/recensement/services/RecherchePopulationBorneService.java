package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.CodeDeptException;
import fr.diginamic.recensement.exceptions.NullChoiceException;
import fr.diginamic.recensement.exceptions.NumberException;
import fr.diginamic.recensement.exceptions.NumberNotGoodException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner)
			throws NumberException, NumberNotGoodException, NullChoiceException, CodeDeptException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		verifierDonneesUtilisateur(choix, saisieMin, saisieMax);

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;

		List<Ville> villes = rec.getVilles();

		verifierCodeDept(choix, villes);

		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
	}

	private void verifierDonneesUtilisateur(String choix, String saisieMin, String saisieMax)
			throws NumberException, NumberNotGoodException, NullChoiceException {

		if (choix == "") {
			throw new NullChoiceException("Le champs code du département est obligatoire");
		}

		if (saisieMin == "") {
			throw new NullChoiceException("Le champs population min est obligatoire");
		}

		if (saisieMax == "") {
			throw new NullChoiceException("Le champs population max est obligatoire");
		}

		for (int i = 0; i < saisieMin.length(); i++) {
			if (saisieMin.charAt(0) == '-') {
				continue;
			}
			if (saisieMin.charAt(i) < '0' || saisieMin.charAt(i) > '9') {
				throw new NumberException("Vous avez saisi une lettre au lieu de chiffre pour le min");
			}
		}

		for (int i = 0; i < saisieMax.length(); i++) {
			if (saisieMax.charAt(0) == '-') {
				continue;
			}
			if (saisieMax.charAt(i) < '0' || saisieMax.charAt(i) > '9') {
				throw new NumberException("Vous avez saisi une lettre au lieu de chiffre pour le max");
			}
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;

		if (min < 0) {
			throw new NumberNotGoodException("Vous avez saisi un min < 0");
		}

		if (max < 0) {
			throw new NumberNotGoodException("Vous avez saisi un max < 0");
		}

		if (min > max) {
			throw new NumberNotGoodException("Vous avez saisi un min > max");
		}
	}

	private void verifierCodeDept(String choix, List<Ville> villes) throws CodeDeptException {
		boolean codeDepExist = false;
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				codeDepExist = true;
				break;
			}
		}

		if (!codeDepExist) {
			throw new CodeDeptException("Vous avez saisi un code departement inconnu");
		}

	}

}
