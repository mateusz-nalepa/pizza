package pl.tu.kielce.pizza.be.pizza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.be.pizza.repository.PizzaExecutor;
import pl.tu.kielce.pizza.common.common.util.NewPriceContextUtils;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private final PizzaExecutor pizzaExecutor;

    private final NewPriceContextUtils newPriceContextUtils;

    @Override
    public PizzaDto findOne(Long pizzaId) {
        PizzaDto pizzaDto = pizzaExecutor.findOne(pizzaId);
        pizzaDto.setPrice(newPriceContextUtils.priceWithMultiplier(pizzaDto.getPrice()));
//        priceContextUtils.setPriceWithMultiplier(userContext.getMultiplier(), pizzaDto.getPrice());
        return pizzaDto;
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

    @Override
//    @Transactional
    public List<PizzaDto> findAllWithMultiplier() {

        return findAll()
                .stream()
                .peek(pizzaDto -> pizzaDto.setPrice(newPriceContextUtils.priceWithMultiplier(pizzaDto.getPrice())))
                .collect(Collectors.toList());
    }


}
