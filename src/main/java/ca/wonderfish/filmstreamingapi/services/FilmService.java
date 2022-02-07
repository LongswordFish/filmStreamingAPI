package ca.wonderfish.filmstreamingapi.services;

import ca.wonderfish.filmstreamingapi.domains.Film;
import ca.wonderfish.filmstreamingapi.exceptions.FilmIdException;
import ca.wonderfish.filmstreamingapi.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Iterable<Film> findAllFilms(){
        return  filmRepository.findAll();
    }

    public Film saveOrUpdateFilm(Film film,Long filmId){
        try{
            //if it's saving new film
            if(filmId==null){
                return filmRepository.save(film);
            }else if(!filmId.equals(film.getId())){
                throw new FilmIdException("Film id - "+film.getId()+" doesn't match ");
            }else{
                return filmRepository.save(film);
            }

        }catch(Exception e){
            throw new FilmIdException("Film id - "+film.getId()+" save failed");
        }
    }

    public Film findFilmByFilmId(Long id){
        Film film = filmRepository.findFilmById(id);
        if(film==null){
            throw new FilmIdException("Film id - "+id+" doesn't exist!");
        }
        return film;
    }

    public boolean deleteFilmById(Long id){
        Film film = filmRepository.findFilmById(id);
        if(film==null){
            throw new FilmIdException("Film id - "+id+" doesn't exist!");
        }
        return true;
    }

}
