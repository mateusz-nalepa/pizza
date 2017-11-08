package pl.tu.kielce.pizza.ingredient.executor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{




    @Query(nativeQuery = true,
    value = "SELECT i.id, i.name, i.description FROM " +
            "item i " +
            "where i.id not in " +
            "   ( select pi.ingredients_id from " +
            "   department d " +
            "   left join pantry p " +
            "   on d.pantry_id = p.id " +
            "   left join pantry_ingredients pi " +
            "   on pi.pantry_id = p.id " +
            "   where d.id = :departmentId ) ")
    List<Object[]> itemsNotAssignedToDepartment(@Param("departmentId") Long departmentId);


    @Query("select i.id from Ingredient  i where i.item.id = :itemId and i.pantryId = :pantryId")
    Long ingredientIdByItemIdAndPantryId(@Param("itemId") Long itemId, @Param("pantryId") Long pantryId );
}
