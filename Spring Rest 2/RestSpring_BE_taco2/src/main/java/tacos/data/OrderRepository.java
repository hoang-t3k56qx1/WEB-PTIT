package tacos.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tacos.Ingredient;
import tacos.Order;
import tacos.web.api.DeleteMapping;
import tacos.web.api.EmptyResultDataAccessException;
import tacos.web.api.PutMapping;

public interface OrderRepository extends CrudRepository<Order, Long>{
	Iterable<Order> findAll();
	Optional<Order> findById(String id);
	Order save(Order Order);
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient postIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepo.save(ingredient);
	}

	rest.postForObject("http://localhost:8080/ingredients",ingredient,Ingredient.class);

	@PutMapping("/{ingredientId}")
	public Order putIngredient(@RequestBody Ingredient ingredient) {
		return ingredientRepo.save(ingredient);
	}

	rest.put("http://localhost:8080/ingredients/{id}",ingredient,ingredient.getId());

	@DeleteMapping("/{ingredientId}")
	public void deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
		try {
			ingredientRepo.deleteById(ingredientId);
		} catch (EmptyResultDataAccessException e) {
		}
	}

	rest.delete("http://localhost:8080/ingredients/{id}",ingredient.getId()):
}

