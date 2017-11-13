package pl.tu.kielce.pizza.be.ingredient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("select i from Ingredient i where i.id in :ingredientsIds ")
    Set<Ingredient> findByIngredientsIds(@Param("ingredientsIds") List<Long> ingredientsIds);
}
