package theMovieDbApi.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import theMovieDbApi.dao.DaoActeur;
import theMovieDbApi.dao.DaoFilm;
//import theMovieDbApi.dao.DaoFilmGenre;
import theMovieDbApi.dao.DaoGenre;
import theMovieDbApi.dao.DaoUtilisateur;
import theMovieDbApi.model.Acteur;
//import model.Acteur;
//import model.Film;
import theMovieDbApi.model.ActeurJson;
import theMovieDbApi.model.ActeursPlayingJson;
import theMovieDbApi.model.Film;
import theMovieDbApi.model.FilmDetailJson;
//import theMovieDbApi.model.FilmGenre;
//import theMovieDbApi.model.FilmNowPlayinJson;
import theMovieDbApi.model.FilmNowPlayingJson;
import theMovieDbApi.model.Genre;
import theMovieDbApi.model.GenreJson;
import theMovieDbApi.model.JsonActeur;
import theMovieDbApi.model.TitleJson;
import theMovieDbApi.model.Utilisateur;

@RestController
public class FilmRestController {

	public String url ="https://api.themoviedb.org/3/search/person?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR&query=acteur&page=1&include_adult=false";
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private DaoFilm daoFilm;
	
	@Autowired
	private DaoGenre daoGenre;
	
	@Autowired
	private DaoActeur daoActeur;
	
	@Autowired
	private DaoUtilisateur daoUtilisateur;
	
	private int indice;
	
	
	// Renvoyer le json de la demande par acteur avec en parametre l'acteur
	@RequestMapping(value = "/acteurJson/{actor}", method = RequestMethod.GET)
	  public JsonActeur getAll(@PathVariable String actor) throws Exception {
		String url_acteur = "https://api.themoviedb.org/3/search/person?include_adult=false&page=1&query="+actor+"&language=en-US&api_key=08b5b405b64319954ebd67db9a6579cd";
		JsonActeur monJson = restTemplate.getForObject(url_acteur, JsonActeur.class);
		System.out.println(monJson.results.get(0).getId());
	    return monJson;
	}
	
	// Renvoyer le json de film en salle actuellement
	/*@RequestMapping(value = "/lesFilmsEnSalle/", method = RequestMethod.GET)
	  public FilmNowPlayingJson getNow() throws Exception {
		String url_now = "https://api.themoviedb.org/3/movie/now_playing?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR&page=1&region=fr";
		FilmNowPlayingJson monJson = restTemplate.getForObject(url_now, FilmNowPlayingJson.class);
		
		System.out.println("Nombre totale de page : "+monJson.getTotal_pages());
		System.out.println("Date max : "+monJson.getDates().getMaximum());
		System.out.println("Date min  : "+monJson.getDates().getMinimum());
		
		// remplir la base de données
		Film film = new Film();
		Genre genre;
		
		for(int i=0; i<monJson.getResults().length; i++)
		{
			//String url_detail = "https://api.themoviedb.org/3/movie/"+monJson.getResults()[i].getId()+"?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR";
			//FilmNowPlayingJson mJson = restTemplate.getForObject(url_detail, FilmNowPlayingJson.class);
			Film filmExisting = daoFilm.find(monJson.getResults()[i].getId());
			
			FilmGenre filmGenre = new FilmGenre();
			
			if(filmExisting == null)
			{
				
				film.setId(monJson.getResults()[i].getId());
				film.setTitle(monJson.getResults()[i].getTitle());
				film.setOverview(monJson.getResults()[i].getOverview());
				film.setRelease_date(monJson.getResults()[i].getRelease_date());
				film.setPoster_path("https://image.tmdb.org/t/p/w185"+monJson.getResults()[i].getPoster_path());
				film.setOriginal_title(monJson.getResults()[i].getOriginal_title());
				
				for(int j=0; j<monJson.getResults()[i].getGenre_ids().length; j++)
				{
					System.out.println("Taille du genre "+monJson.getResults()[i].getGenre_ids().length);
					System.out.println("ID du genre "+monJson.getResults()[i].getGenre_ids()[j].longValue());
					genre = daoGenre.find(monJson.getResults()[i].getGenre_ids()[j].longValue());
					System.out.println("Nom du genre : "+genre.getName());
					//filmGenre.setGenre(genre);
				}
				//System.out.println(monJson.getResults()[i].getId());
				daoFilm.create(film);
				filmGenre.setFilm(film);
				//daoFilmGenre.create(filmGenre);
			}
			else
			{
				film = filmExisting;
			}
			//System.out.println(monJson.getResults()[i].getId());
		}
	    return monJson;

	}*/
	
	
	// Renvoyer le json des genres actuellement et les enregistre en BD à faire en 1er
		@RequestMapping(value = "/lesGenres/", method = RequestMethod.GET)
		  public GenreJson getGenre() throws Exception {
			String url_genre = "https://api.themoviedb.org/3/genre/movie/list?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR";
			GenreJson monJson = restTemplate.getForObject(url_genre, GenreJson.class);
			
			// remplissage la base de données
			Genre genre = new Genre();
			for(int i=0; i<monJson.getGenres().size(); i++)
			{
				
				Genre genreExisting = daoGenre.find(monJson.genres.get(i).getId());
				if(genreExisting == null)
				{
					
					genre.setId(monJson.genres.get(i).getId());
					genre.setName(monJson.genres.get(i).getName());
					
					System.out.println(monJson.genres.get(i).getId());
					daoGenre.create(genre);
				}
				else
				{
					genre = genreExisting;
				}
				System.out.println("Genre id"+i+" "+monJson.getGenres().get(i).getId());
				
			}
		    return monJson;

		}
	
	// Renvoyer le json de film par titre
		@RequestMapping(value = "/titleJson/{title}", method = RequestMethod.GET)
		  public TitleJson getTitle(@PathVariable String title) throws Exception {
			String url_title = "https://api.themoviedb.org/3/search/movie?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR&query="+title+"&page=1&include_adult=false";
			TitleJson monJson = restTemplate.getForObject(url_title, TitleJson.class);
			System.out.println(monJson.results.get(0).getId());
		    return monJson;
		}
		
	// renvoyer les details par id des films en salle actuellement : à exécuter en 1er après les genre
		
		@RequestMapping(value = "/lesFilmsEnSalleDetail/", method = RequestMethod.GET)
		  public FilmNowPlayingJson getNowDetail() throws Exception {
			String url_now = "https://api.themoviedb.org/3/movie/now_playing?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR&page=1&region=fr";
			FilmNowPlayingJson monJson = restTemplate.getForObject(url_now, FilmNowPlayingJson.class);
			
			
			System.out.println("Nombre totale de page : "+monJson.getTotal_pages());
			System.out.println("Date max : "+monJson.getDates().getMaximum());
			System.out.println("Date min  : "+monJson.getDates().getMinimum());
			
			// remplissage de la base de données
			Film film = new Film();
			Genre genre;
			
			for(int i=0; i<monJson.getResults().length; i++)
			{
				String url_detail;
				url_detail = "https://api.themoviedb.org/3/movie/"+monJson.getResults()[i].getId()+"?api_key=08b5b405b64319954ebd67db9a6579cd&language=fr-FR";
				FilmDetailJson jsonDetail = restTemplate.getForObject(url_detail, FilmDetailJson.class);
				Film filmExisting = daoFilm.find(jsonDetail.getId());
				
				List<Genre> genres = new ArrayList<Genre>();
				if(filmExisting == null)
				{
					
					film.setId(jsonDetail.getId());
					film.setTitle(jsonDetail.getTitle());
					film.setOverview(jsonDetail.getOverview());
					film.setRelease_date(jsonDetail.getRelease_date());
					film.setPoster_path("https://image.tmdb.org/t/p/w185"+jsonDetail.getPoster_path());
					film.setOriginal_title(jsonDetail.getOriginal_title());
					
					for(int j=0; j<jsonDetail.getGenres().length; j++)
					{
						System.out.println("Taille du genre "+jsonDetail.getGenres().length);
						System.out.println("ID du genre "+jsonDetail.getGenres()[j].getId());
						genre = daoGenre.find(jsonDetail.getGenres()[j].getId());
						System.out.println("Nom du genre : "+genre.getName());
						genres.add(genre);

					}
					film.setGenres(genres);
					daoFilm.create(film);
				}
				else
				{
					film = filmExisting;
					film.setRelease_date(jsonDetail.getRelease_date());
					daoFilm.update(film);
					
				}
				
			}
		    return monJson;

		}
		
		/* */
		
		// à exécuter en 2ème après les genres pour relier les films aux acteurs
		@RequestMapping(value = "/lesFilmsEnSalleActeurs/", method = RequestMethod.GET)
		  public String getNowActeurs() throws Exception {
			List<Film> films = daoFilm.findAll();
			List<Acteur> acteurs = new ArrayList<Acteur>();
			
			String url_acteurs;
			Acteur acteur = new Acteur();
			
				for(Film film : films)
				{
					url_acteurs = "https://api.themoviedb.org/3/movie/"+film.getId()+"/credits?api_key=08b5b405b64319954ebd67db9a6579cd";
					ActeursPlayingJson monJson = restTemplate.getForObject(url_acteurs, ActeursPlayingJson.class);
					if(monJson.getCast() != null){
						for(int j=0; j<monJson.getCast().length; j++)
						{
							if(daoActeur.find(monJson.getCast()[j].getId()) == null )
							{
								acteur = new Acteur();
								acteur.setCast_id(monJson.getCast()[j].getCast_id());
								acteur.setCharacter(monJson.getCast()[j].getCharacter());
								acteur.setName(monJson.getCast()[j].getName());
								acteur.setId(monJson.getCast()[j].getId());
								acteur.setOrdre(monJson.getCast()[j].getOrdre());
								acteur.setCredit_id(monJson.getCast()[j].getCredit_id());
								acteur.setProfile_path("https://image.tmdb.org/t/p/w185"+monJson.getCast()[j].getProfile_path());
								
								daoActeur.create(acteur);
								acteurs.add(acteur);
								
								System.out.println(monJson.getCast()[j].getName());
							}
							
							
						}
						
						film.setActeurs(acteurs);
						daoFilm.update(film);
						
					}
					else
					{
						System.out.println("monJson.getCast() est null");
					}
				}
				
		    return "succès";

		}
		/* */
		/*Debut des appels par le client  */
		
		@RequestMapping(value = "/nowPlayingFilm/", method = RequestMethod.GET)
		public ResponseEntity<List<Film>> listAllNow() {
			List<Film> films = daoFilm.findAll();
			if (films == null) {
				return new ResponseEntity<List<Film>>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
			}
		}
		
		// recherche par genre
		@RequestMapping(value = "/getFilmByGenre/{genre}", method = RequestMethod.GET)
		public ResponseEntity<List<Film>> getFilmByGenre(@PathVariable String genre) {
			List<Film> films = daoFilm.getByGenre(genre);
			if (films == null) {
				return new ResponseEntity<List<Film>>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
			}
		}
		
		// recherche par titre
		@RequestMapping(value = "/getFilmByTitle/{titre}", method = RequestMethod.GET)
		public ResponseEntity<List<Film>> getFilmByTitle(@PathVariable String titre) {
			List<Film> films = daoFilm.getByTitle(titre);
			if (films == null) {
					return new ResponseEntity<List<Film>>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
				}
		}
		
		// liste des genres
		@RequestMapping(value = "/getAllGenre/", method = RequestMethod.GET)
		public ResponseEntity<List<Genre>> getAllGenre() {
			List<Genre> genres = daoGenre.findAll();
			if (genres == null) {
					return new ResponseEntity<List<Genre>>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
				}
		}
		
		
		// liste des acteurs
		@RequestMapping(value = "/getAllActors/", method = RequestMethod.GET)
		public ResponseEntity<List<Acteur>> getAllActors() {
			List<Acteur> acteurs = daoActeur.findAll();
			if (acteurs == null) {
				return new ResponseEntity<List<Acteur>>(HttpStatus.NO_CONTENT);
			} else {
				for(Acteur acteur : acteurs)
				{
					System.out.println(acteur.name);
				}
				return new ResponseEntity<List<Acteur>>(acteurs, HttpStatus.OK);
			}
		}
		
		
		// recherche par acteur
		@RequestMapping(value = "/getFilmByActorName/{actorName}", method = RequestMethod.GET)
		public ResponseEntity<List<Film>> getFilmByActor(@PathVariable String actorName) {
			List<Film> films = daoFilm.getByActor(actorName);
			if (films == null) {
				return new ResponseEntity<List<Film>>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
			}
		}
		
		
		// retourne les details d'un film par id
		@RequestMapping(value = "/getFilmDetail/{id}", method = RequestMethod.GET)
		public ResponseEntity<Set<Film>> getFilmDetail(@PathVariable Long id) {
			Set<Film> films = daoFilm.getDetailFilm(id);
			if (films == null) {
				return new ResponseEntity<Set<Film>>(HttpStatus.NO_CONTENT);
			} else {
			return new ResponseEntity<Set<Film>>(films, HttpStatus.OK);
			}
		}
		
		// ajouter un film à ma videoThèque
		@RequestMapping(value="/addfilmToVideoTheque/{id}/{username}", method = RequestMethod.GET)
		public ResponseEntity<String> addfilmToVideoTheque(@PathVariable Long id,@PathVariable String username)
		{
			Film film = daoFilm.find(id) != null ?daoFilm.find(id) :new Film();
			Utilisateur utilisateur = daoUtilisateur.findByUsername(username) != null ? daoUtilisateur.findByUsername(username) : new Utilisateur();
			List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
			if(film != null && utilisateur != null)
			{
				utilisateurs = film.getUtilisateurs(); // je recupère la liste existante
				
				utilisateurs.add(utilisateur);
//				for(Utilisateur u :utilisateurs)
//				{
//					System.out.println("Id: "+u.getId());
//					System.out.println("Email: "+u.getEmail());
//					System.out.println("Username: "+u.getUsername());
//				}
				film.setUtilisateurs(utilisateurs);
				daoFilm.update(film);
			}
			
			String s = "Succès";
			if (s == null) {
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<String>(s, HttpStatus.OK);
			}
		}
		
		// enlever un film à la videotheque
		@RequestMapping(value = "/deletefilmToVideoTheque/{id}/{username}", method = RequestMethod.PUT)
		public ResponseEntity<Film> delete(@PathVariable("id") Long id,@PathVariable("username") String username) {
			Film film = daoFilm.find(id)!=null ?daoFilm.find(id):new Film();
			Utilisateur user = daoUtilisateur.findByUsername(username)!= null ?daoUtilisateur.findByUsername(username):new Utilisateur();
			if (film == null && user==null) {
				return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
			}
			List<Utilisateur> users = film.getUtilisateurs();
			
			for(int i=0; i<users.size(); i++)
			{
				if(users.get(i) == user)
				indice =i;
			}
			
			users.remove(indice);
			film.setUtilisateurs(users);
			daoFilm.update(film);
			return new ResponseEntity<Film>(HttpStatus.NO_CONTENT);
		}

		// retourne la liste des films dans la videothèque de l'utilisateur
		@RequestMapping(value = "/getVideoTheque/{username}", method = RequestMethod.GET)
		public ResponseEntity<Set<Film>> getVideoTheque(@PathVariable String username) {
			Set<Film> films = daoFilm.getVideoTheque(username);
			if (films == null) {
				return new ResponseEntity<Set<Film>>(HttpStatus.NO_CONTENT);
			} else {
			return new ResponseEntity<Set<Film>>(films, HttpStatus.OK);
			}
		}
		
		// ajouter un utilisateur
		@RequestMapping(value = "/utilisateur/", method = RequestMethod.POST)
		public ResponseEntity<Void> create(@RequestBody Utilisateur utilisateur, UriComponentsBuilder ucBuilder) {
			if (utilisateur.getId() != null && daoUtilisateur.find(utilisateur.getId()) != null) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			} else {
				daoUtilisateur.create(utilisateur);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/utilisateur/{id}").buildAndExpand(utilisateur.getId()).toUri());
				return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			}
		}
		
		// recherche d'un utlisateur par son id
		@RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.GET)
		public ResponseEntity<Utilisateur> get(@PathVariable("id") Long id) {
			Utilisateur utilisateur = daoUtilisateur.find(id);
			if (utilisateur == null) {
				return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.OK);
			}
		}
		
		
		
		/* fin des appels par le client */
	
}
