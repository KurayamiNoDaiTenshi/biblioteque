package fr.biblioteque.web;

import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import fr.biblioteque.business.GenericService;
import fr.biblioteque.business.GenericServiceImpl;
import fr.biblioteque.dao.entity.Auteur;

@WebServlet(urlPatterns = { "/auteur", "/auteur/*" })
public class AuteurServlet extends HttpServlet {

	private GenericService<Auteur> auteurService;

	public AuteurServlet() {
		auteurService = new GenericServiceImpl<Auteur>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		Auteur auteur = auteurService.findById(Auteur.class, Integer.parseInt(request.getPathInfo().substring(1)));
		if (auteur == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("\"message\" : \"Auteur non trouvé\"}");
		} else {
			JSONObject auteurJson = new JSONObject();
			auteurJson.put("auteur", auteur);

			response.getWriter().append(auteurJson.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject bodyJson = new JSONObject(body);
		String nom = bodyJson.getString("nom");
		String prenom = bodyJson.getString("prenom");
		String langue = bodyJson.getString("langue");
		if (nom.isEmpty() || nom == null || prenom.isEmpty() || prenom == null || langue.isEmpty() || langue == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.print("{\"message\" : \"Requête mal formée merci de vérifier les paramêtres saisi! \"}");
		} else {
			Auteur auteur = new Auteur(nom, prenom, langue);
			auteurService.insert(auteur);
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_CREATED);
			response.getWriter().append("{\"message\":\"Auteur inséré en base\"}");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject bodyJson = new JSONObject(body);
		String nom = bodyJson.getString("nom");
		String prenom = bodyJson.getString("prenom");
		String langue = bodyJson.getString("langue");
		if (nom.isEmpty() || nom == null || prenom.isEmpty() || prenom == null || langue.isEmpty() || langue == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.print("{\"message\" : \"Requête mal formée merci de vérifier les paramêtres saisi! \"}");
		} else {
			Auteur auteur = auteurService.findById(Auteur.class, Integer.parseInt(request.getPathInfo().substring(1)));
			auteur.setNom(nom);
			auteur.setPrenom(prenom);
			auteur.setLangue(langue);
			auteurService.update(auteur);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		Auteur auteur = auteurService.findById(Auteur.class, Integer.parseInt(request.getPathInfo().substring(1)));
		if (auteur == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("\"message\" : \"Auteur non trouvé\"}");
		} else {
			auteurService.delete(auteur);

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().append("\"message\" : \"Auteur supprimé\"}");
		}
	}
}
