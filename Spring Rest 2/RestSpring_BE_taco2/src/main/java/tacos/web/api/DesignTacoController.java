package tacos.web.api;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
	private TacoRepository tacoRepo;
	@Autowired
	EntityLinks entityLinks;

	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		return tacoRepo.findAll();
	}

	@GetMapping("/{id}")
	public Taco tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			return optTaco.get();
		}
		return null;
	}

	@PostMapping
	 public String processDesign(@RequestParam("ingredients") String
	ingredientIds, @RequestParam("name") String name) {
	 List<Ingredient> ingredients = new ArrayList<Ingredient>();
	 for (String ingredientId : ingredientIds.split(",")) {
	 Ingredient ingredient = rest.getForObject("http://localhost:8080/
	 ingredients/{id}",Ingredient.class, ingredientId);
	 ingredients.add(ingredient);
	 }
	 Taco taco = new Taco();
	 taco.setName(name);
	 taco.setIngredients(ingredients);
	 System.out.println(taco);
	 rest.postForObject("http://localhost:8080/design", taco, Taco.class);
	 return "redirect:/orders/current";
	 }

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient postIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepo.save(ingredient);
	}

	rest.postForObject("http://localhost:8080/ingredients",ingredient,Ingredient.class);
	
	@PutMapping("/{ingredientId}")
	public Order putIngredient(@RequestBody Ingredient
	ingredient) {
	return ingredientRepo.save(ingredient);
	}
	
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}
}