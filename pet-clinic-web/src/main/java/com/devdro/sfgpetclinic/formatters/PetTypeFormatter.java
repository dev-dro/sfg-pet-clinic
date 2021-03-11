package com.devdro.sfgpetclinic.formatters;

import com.devdro.sfgpetclinic.model.PetType;
import com.devdro.sfgpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String name, Locale locale) throws ParseException {
        return petTypeService.findByName(name)
                .orElseThrow(() -> new ParseException("type not found: " + name, 0));
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
