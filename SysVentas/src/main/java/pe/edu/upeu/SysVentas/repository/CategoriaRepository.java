package pe.edu.upeu.SysVentas.repository;

import org.springframework.stereotype.Repository;
import pe.edu.upeu.SysVentas.model.Categoria;

@Repository
public interface CategoriaRepository extends ICrudGenericoRepository<Categoria, Long> {
}