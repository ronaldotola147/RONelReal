package pe.edu.upeu.SysVentas.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.SysVentas.model.Categoria;
import pe.edu.upeu.SysVentas.repository.CategoriaRepository;
import pe.edu.upeu.SysVentas.repository.ICrudGenericoRepository;
import pe.edu.upeu.SysVentas.service.ICategoriaService;
@RequiredArgsConstructor
public class CategoriaServiceImp extends CrudGenericoServiceImp<Categoria,Long> implements ICategoriaService {


    private final CategoriaRepository categoriaRepository;

    @Override
    protected ICrudGenericoRepository<Categoria, Long> getRepo() {
        return categoriaRepository;
    }
}
