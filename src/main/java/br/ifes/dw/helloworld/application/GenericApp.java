package br.ifes.dw.helloworld.application;

import br.ifes.dw.helloworld.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public abstract class GenericApp<T, ID, DTO>
{
    @Autowired
    private ModelMapper modelMapper;
    private final JpaRepository<T, ID> repository;

    public GenericApp(JpaRepository<T, ID> repository)
    {
        this.repository = repository;
    }

    public T save(DTO dto)
    {
        T model;

        model = modelMapper.map(dto, getType());

        return this.repository.save(model);
    }

    public T retrieve(ID id) throws NotFoundException
    {
        T model;
        Optional<T> entity;

        entity = this.repository.findById(id);

        if (entity.isPresent()) model = entity.get();
        else throw new NotFoundException();

        return model;
    }

    public List<T> retrieveAll()
    {
        return repository.findAll();
    }

    public void update(ID id, DTO dto)
    {
        T model;

        try
        {
            model = retrieve(id);
            model = modelMapper.map(dto, getType());
            repository.save(model);
        }
        catch (NotFoundException e)
        {
            e.getMessage();
        }
    }

    public void delete(ID id)
    {
        repository.deleteById(id);
    }

    protected abstract Class<T> getType();
}
