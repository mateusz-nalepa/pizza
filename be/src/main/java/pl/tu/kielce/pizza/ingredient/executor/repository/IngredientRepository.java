package pl.tu.kielce.pizza.ingredient.executor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
