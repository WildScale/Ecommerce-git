

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oussama.dao.DAOAdresse;
import com.oussama.dao.DAOClient;
import com.oussama.dao.DAOFamille;
import com.oussama.dao.DAOProduit;
import com.oussama.models.Adresse;
import com.oussama.models.Client;
import com.oussama.models.Famille;
import com.oussama.models.Produit;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet({ "/ajouterFamille", "/ajouterProduit","/index","/produit","/famille","/produit-modifier",
	"/produit-supprimer","/inscription","/register"})
public class MyServlet extends HttpServlet {
	private DAOFamille daoFamille;
	private DAOProduit daoProduit;
	private DAOAdresse daoAdresse;
	private DAOClient daoClient;
	@Override
	public void init() throws ServletException {

		super.init();
		daoFamille = new DAOFamille();
		daoProduit = new DAOProduit();
		daoClient = new DAOClient();
		daoAdresse = new DAOAdresse();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getServletPath().equals("/ajouterProduit")) {
			ArrayList<Famille> familles = daoFamille.listerFamille();
			
			request.setAttribute("familles", familles);
			
			this.getServletContext().getRequestDispatcher("/ajouterProduit.jsp").forward(request, response);
		}
		else if(request.getServletPath().equals("/index")) {
		
			ArrayList<Produit> produits = daoProduit.listerProduits();
			ArrayList<Famille> familles = daoFamille.listerFamille();
			request.setAttribute("produits", produits);
			request.setAttribute("familles", familles);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if(request.getServletPath().equals("/produit")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			Produit produit = daoProduit.getProduitParId(id);
			
			request.setAttribute("produit", produit);
			
			this.getServletContext().getRequestDispatcher("/produitProfile.jsp").forward(request, response);
		}
		else if(request.getServletPath().equals("/famille")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("ID FAMILLE : "+ id);
			ArrayList<Produit> produits = daoProduit.getProduitParFamille(id);
			request.setAttribute("produits", produits);
			System.out.println(produits.toString());
			request.setAttribute("famille", produits.get(0).getFamille());
			this.getServletContext().getRequestDispatcher("/category.jsp").forward(request, response);
		}
		
		else if(request.getServletPath().equals("/produit-modifier")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Produit produit = daoProduit.getProduitParId(id);
			request.setAttribute("produit", produit);
			ArrayList<Famille> familles = daoFamille.listerFamille();
			request.setAttribute("familles", familles);
			this.getServletContext().getRequestDispatcher("/modifierP.jsp").forward(request, response);
		}
		else if(request.getServletPath().equals("/register")) {
			this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/ajouterFamille")) {
			Famille famille = new Famille();
			String nom = request.getParameter("nom_famille");
			famille.setNom(nom);

			daoFamille.ajouterFamille(famille);
			System.out.println("Ajouté avec succès ");

		}
		else if(request.getServletPath().equals("/ajouterProduit") ) {
			Produit produit = new Produit();
			
			String nom = request.getParameter("nom_produit");
			double prix = Double.parseDouble(request.getParameter("prix_produit"));
			int id_famille = Integer.parseInt(request.getParameter("famille_produit"));
			
			produit.setNom(nom);
			produit.setPrix(prix);
			Famille famille = daoProduit.getFamilleParId(id_famille);
			produit.setFamille(famille);
			
			System.out.println(produit);
			
			daoProduit.ajouterProduit(produit);
		}
		
		else if(request.getServletPath().equals("/produit-supprimer")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			daoProduit.supprimerProduit(id);
			
			response.sendRedirect("/Ecommerce/index");
			
		}
		else if(request.getServletPath().equals("/produit-modifier")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			String nom = request.getParameter("nom_produit");
			double prix = Double.parseDouble(request.getParameter("prix_produit"));
			int id_famille = Integer.parseInt(request.getParameter("famille_produit"));
			
			Famille famille = daoProduit.getFamilleParId(id_famille);
			
			Produit produit = new Produit(nom,prix,famille);
			
			daoProduit.modifierProduit(produit, id);
			
			response.sendRedirect("/Ecommerce/produit?id="+id);
		}
		
		else if(request.getServletPath().equals("/famille/supprimer")) {
			int id =  Integer.parseInt(request.getParameter("id"));
			
			daoFamille.supprimerFamille(id);
			
			response.sendRedirect("/index");
		}
		else if(request.getServletPath().equals("/inscription")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String mdp = request.getParameter("password");
			
			String ville = request.getParameter("ville");
			String rue = request.getParameter("rue");
			String quartier = request.getParameter("quartier");
			
			Adresse adresse = new Adresse(ville, quartier, rue);
			
			adresse = daoAdresse.ajouterAdresse(adresse);
			
			Client client = new Client( nom, prenom, adresse.getId(), email, mdp);
			daoClient.ajouterClient(client);
			HttpSession session = request.getSession();
			session.setAttribute("client", client);
			
			response.sendRedirect("/Ecommerce/index");
		}

	}

}
