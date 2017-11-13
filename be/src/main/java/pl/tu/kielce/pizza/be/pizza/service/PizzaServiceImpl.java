package pl.tu.kielce.pizza.be.pizza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.be.pizza.repository.PizzaExecutor;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private final PizzaExecutor pizzaExecutor;

    @Override
    public PizzaDto findOne(Long pizzaId) {
        return pizzaExecutor.findOne(pizzaId);
    }

    @Override
    @Transactional
    public PizzaDto create(PizzaDto pizzaDto) {
        return pizzaExecutor.create(pizzaDto);
    }

    @Override
    @Transactional
    public List<PizzaDto> findAll() {
        return pizzaExecutor.findAll();
    }
}
