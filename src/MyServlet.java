
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.oussama.dao.DAOAdresse;
import com.oussama.dao.DAOClient;
import com.oussama.dao.DAOFamille;
import com.oussama.dao.DAOMailing;
import com.oussama.dao.DAOProduit;
import com.oussama.models.Adresse;
import com.oussama.models.Client;
import com.oussama.models.Famille;
import com.oussama.models.Produit;


@WebServlet({ "/ajouterFamille", "/ajouterProduit", "/index", "/produit", "/famille", "/produit-modifier",
		"/produit-supprimer", "/inscription", "/register", "/panier", "/retirer-produit","/login","/contact","/mail","/checkout" })
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)
public class MyServlet extends HttpServlet {
	private DAOFamille daoFamille;
	private DAOProduit daoProduit;
	private DAOAdresse daoAdresse;
	private DAOClient daoClient;
	private DAOMailing daoMailing;
	
	private static String dossier_images = "images_produits";

	@Override
	public void init() throws ServletException {

		super.init();
		daoFamille = new DAOFamille();
		daoProduit = new DAOProduit();
		daoClient = new DAOClient();
		daoAdresse = new DAOAdresse();
		daoMailing = new DAOMailing();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/ajouterProduit")) {
			ArrayList<Famille> familles = daoFamille.listerFamille();

			request.setAttribute("familles", familles);

			this.getServletContext().getRequestDispatcher("/ajouterProduit.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/index")) {

			ArrayList<Produit> produits = daoProduit.listerProduits();
			ArrayList<Famille> familles = daoFamille.listerFamille();
			request.setAttribute("produits", produits);
			request.setAttribute("familles", familles);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/produit")) {

			int id = Integer.parseInt(request.getParameter("id"));

			Produit produit = daoProduit.getProduitParId(id);

			request.setAttribute("produit", produit);

			this.getServletContext().getRequestDispatcher("/produitProfile.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/famille")) {

			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("ID FAMILLE : " + id);
			ArrayList<Produit> produits = daoProduit.getProduitParFamille(id);
			request.setAttribute("produits", produits);
			System.out.println(produits.toString());
			request.setAttribute("famille", produits.get(0).getFamille());
			this.getServletContext().getRequestDispatcher("/category.jsp").forward(request, response);
		}

		else if (request.getServletPath().equals("/produit-modifier")) {
			int id = Integer.parseInt(request.getParameter("id"));

			Produit produit = daoProduit.getProduitParId(id);
			request.setAttribute("produit", produit);
			ArrayList<Famille> familles = daoFamille.listerFamille();
			request.setAttribute("familles", familles);
			this.getServletContext().getRequestDispatcher("/modifierP.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/register")) {
			this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		} else if (request.getServletPath().equals("/panier")) {
			HttpSession session = request.getSession();
			HashMap<Produit, Integer> produits = (HashMap<Produit, Integer>) session.getAttribute("produits");

			if (produits == null) {
				produits = new HashMap<Produit, Integer>();
				session.setAttribute("produits", produits);
			}

			request.setAttribute("produits", produits);

			this.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
		}
		else if(request.getServletPath().equals("/contact")) {
			this.getServletContext().getRequestDispatcher("/contact.jsp").forward(request, response);
		}
		else if(request.getServletPath().equals("/checkout")) {
			HttpSession session = request.getSession();
			Client client = (Client) session.getAttribute("client");
			
			
			if(client == null) {
				response.sendRedirect("/Ecommerce/login");
			}
			else {
				HashMap<Produit, Integer> produits = (HashMap<Produit, Integer>) session.getAttribute("produits");
				
				if(produits  == null) {
					produits = new HashMap<Produit, Integer>();
					session.setAttribute("produits", produits);
				}
				
				request.setAttribute("client", client);
				request.setAttribute("produits", produits);
				this.getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
			}
			
		}
		else if(request.getServletPath().equals("/login")) {
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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

		} else if (request.getServletPath().equals("/ajouterProduit")) {
			Produit produit = new Produit();

			String nom = request.getParameter("nom_produit");
			double prix = Double.parseDouble(request.getParameter("prix_produit"));
			int id_famille = Integer.parseInt(request.getParameter("famille_produit"));
			String description = request.getParameter("description");

			produit.setNom(nom);
			produit.setPrix(prix);
			Famille famille = daoProduit.getFamilleParId(id_famille);
			produit.setFamille(famille);
			produit.setDescription(description);
			
			PrintWriter writer = response.getWriter();
			String path = "C:\\Users\\Yassine\\eclipse-workspace\\Ecommerce\\WebContent\\images_produits";
			File fileSaveDir =  new File(path);
			
			if(!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			
			Part part=request.getPart("image");
	        String fileName=extractFileName(part);
	        part.write(path + File.separator + fileName);
	        produit.setDescription(description);
			System.out.println("FILE NAME : "+fileName);
			
			produit.setImage(fileName);
			System.out.println(produit.getImage());
			
			daoProduit.ajouterProduit(produit);
			
			response.sendRedirect("/Ecommerce/index");
		}

		else if (request.getServletPath().equals("/produit-supprimer")) {
			int id = Integer.parseInt(request.getParameter("id"));

			daoProduit.supprimerProduit(id);

			response.sendRedirect("/Ecommerce/index");

		} else if (request.getServletPath().equals("/produit-modifier")) {
			int id = Integer.parseInt(request.getParameter("id"));

			String nom = request.getParameter("nom_produit");
			double prix = Double.parseDouble(request.getParameter("prix_produit"));
			int id_famille = Integer.parseInt(request.getParameter("famille_produit"));
			String description = request.getParameter("description");
			
			Famille famille = daoProduit.getFamilleParId(id_famille);
			
			
			Produit produit = new Produit(nom, prix, famille, description, daoProduit.getProduitParId(id).getImage());
			daoProduit.modifierProduit(produit, id);

			response.sendRedirect("/Ecommerce/produit?id=" + id);
		}

		else if (request.getServletPath().equals("/famille/supprimer")) {
			int id = Integer.parseInt(request.getParameter("id"));

			daoFamille.supprimerFamille(id);

			response.sendRedirect("/index");
		} else if (request.getServletPath().equals("/inscription")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String mdp = request.getParameter("password");

			String ville = request.getParameter("ville");
			String rue = request.getParameter("rue");
			String quartier = request.getParameter("quartier");

			Adresse adresse = new Adresse(ville, quartier, rue);

			adresse = daoAdresse.ajouterAdresse(adresse);

			Client client = new Client(nom, prenom, adresse.getId(), email, mdp);
			daoClient.ajouterClient(client);
			HttpSession session = request.getSession();
			session.setAttribute("client", client);

			response.sendRedirect("/Ecommerce/index");
		} else if (request.getServletPath().equals("/panier")) {
			int id = Integer.parseInt(request.getParameter("id"));

			int quantite = Integer.parseInt(request.getParameter("quantite"));
			HttpSession session = request.getSession();
			HashMap<Produit, Integer> produits = (HashMap<Produit, Integer>) session.getAttribute("produits");

			if (produits == null) {
				produits = new HashMap<Produit, Integer>();
				session.setAttribute("produits", produits);
			}

			Produit produit = daoProduit.getProduitParId(id);

			produits.put(produit, quantite);

			response.sendRedirect("/Ecommerce/panier");
		}
		else if(request.getServletPath().equals("/retirer-produit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			HttpSession session = request.getSession();
			HashMap<Produit, Integer > produits = (HashMap<Produit, Integer>) session.getAttribute("produits");
			
			if(produits == null) {
				produits = new HashMap<Produit, Integer>();
				session.setAttribute("produits", produits);
			}
			
			 Iterator it = produits.entrySet().iterator();
			   while (it.hasNext())
			   {
			      Entry<Produit,Integer> item =(Entry<Produit,Integer>) it.next();
			      if(id == item.getKey().getId()) {
			    	  it.remove();
			      }
			      
			   }
			
			response.sendRedirect("/Ecommerce/panier");
		}
		else if(request.getServletPath().equals("/login")) {
			
			System.out.println("INSIDE /LOGIN");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Client client = daoClient.login(email, password);
			
			if(client != null) {
				System.out.println("LOGIN OK");
				HttpSession session = request.getSession();
				session.setAttribute("client", client );
				response.sendRedirect("/Ecommerce/index");
			}
			else {
				System.out.println("erreur :  LOGIN INCORRECT");
				request.setAttribute("erreur", "Vos identifiants sont incorrects.");
				response.sendRedirect("/Ecommerce/login");
			}
		}
		else if(request.getServletPath().equals("/mail")) {
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String objet = request.getParameter("objet");
			String message = request.getParameter("message");
			
			try {
				daoMailing.envoyerMail(message, objet, email);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("/Ecommerce/contact");
		}

	}
	private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	    }
	    return "";
	}

}
