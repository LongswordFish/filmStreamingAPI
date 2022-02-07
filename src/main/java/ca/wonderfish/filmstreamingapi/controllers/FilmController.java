package ca.wonderfish.filmstreamingapi.controllers;

import ca.wonderfish.filmstreamingapi.domains.Film;
import ca.wonderfish.filmstreamingapi.services.FilmService;
import ca.wonderfish.filmstreamingapi.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/films")
@CrossOrigin
public class FilmController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private FilmService filmService;

    @GetMapping("")
    public ResponseEntity<?> getAllFilms(){
        var filmList = filmService.findAllFilms();
        return new ResponseEntity<Iterable<Film>>(filmList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewFilm(@Valid @RequestBody Film newFilm, BindingResult result){
        ResponseEntity<?> hasErrors= mapValidationErrorService.MapValidationService(result);
        if(hasErrors==null){
            Film savedFilm = filmService.saveOrUpdateFilm(newFilm);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + savedFilm.getId()).build().toUri();
            // return new ResponseEntity<Object>(uri, HttpStatus.CREATED);
            return ResponseEntity.created(uri).build();
        }else{
            return hasErrors;
        }
    }

}
