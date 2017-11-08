package pl.tu.kielce.pizza.ingredient.executor;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.common.queryHandler.NativeResultQuerySetHandler;
import pl.tu.kielce.pizza.department.repository.DepartmentRepository;
import pl.tu.kielce.pizza.ingredient.dto.FreeItemDto;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.executor.repository.IngredientRepository;
import pl.tu.kielce.pizza.ingredient.mapper.IngredientMapper;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;
import pl.tu.kielce.pizza.pantry.model.jpa.Pantry;
import pl.tu.kielce.pizza.pantry.repository.PantryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IngredientExecutor {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    private final IngredientMapper ingredientMapper;

    @Autowired
    private final PantryRepository pantryRepository;

    @Autowired
    private final IngredientRepository ingredientRepository;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Transactional
    public IngredientDto add(IngredientDto ingredientDto) {
        Pantry pantryEntity = pantryRepository.findOne(ingredientDto.getPantryId());
        List<Ingredient> ingredients = pantryEntity.getIngredients();
        List<Ingredient> updatedIngredients = new ArrayList<>(ingredients);
        Ingredient entity = ingredientMapper.dtoToEntity(ingredientDto);
        entity = ingredientRepository.save(entity);
        Long id = entity.getId();
        updatedIngredients.add(entity);
        pantryEntity.setIngredients(updatedIngredients);
        pantryRepository.save(pantryEntity);
        ingredientDto.setId(id);
        return ingredientDto;

    }

    public List<FreeItemDto> itemsNotAssignedToDepartment(Long departmentId) {


        List<Object[]> objects = ingredientRepository.itemsNotAssignedToDepartment(departmentId);

        return NativeResultQuerySetHandler.resultList(objects, FreeItemDto.class);
    }

    public void addIngredientToDepartment(IngredientDto ingredientDto, Long departmentId) {
        Long pantryId = departmentRepository.fetchPantryId(departmentId);

        Query query = em.createNativeQuery("insert INTO ingredient(quantity, item_id, pantry_id) VALUES(:quantity, :itemId, :pantryId)");

        query.setParameter("quantity", ingredientDto.getQuantity());
        query.setParameter("itemId", ingredientDto.getItemDto().getId());
        query.setParameter("pantryId", pantryId);

        query.executeUpdate();


        Long ingredientId = ingredientRepository.ingredientIdByItemIdAndPantryId(ingredientDto.getItemDto().getId(), pantryId);


        Query nativeQuery = em.createNativeQuery("insert into pantry_ingredients " +
                "VALUES(:pantryId,:ingredientId)");

        nativeQuery.setParameter("pantryId", pantryId);
        nativeQuery.setParameter("ingredientId", ingredientId);
        nativeQuery.executeUpdate();

    }
}
