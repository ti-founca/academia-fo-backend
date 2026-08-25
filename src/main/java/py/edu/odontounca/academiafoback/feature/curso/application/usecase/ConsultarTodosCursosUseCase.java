package py.edu.odontounca.academiafoback.feature.curso.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.curso.application.dto.CursoInfo;
import py.edu.odontounca.academiafoback.feature.curso.application.mapper.CursoInfoMapper;
import py.edu.odontounca.academiafoback.feature.curso.domain.model.Curso;
import py.edu.odontounca.academiafoback.feature.curso.domain.repository.CursoRepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

@Service
@RequiredArgsConstructor
public class ConsultarTodosCursosUseCase {
    private final CursoRepository cursoRepository;
    private final CursoInfoMapper cursoInfoMapper;

    public Result execute(Query query){
        Page<Curso> cursoPage = this.cursoRepository.consultarTodos(query.filter, query.pageRequest);
        return new Result(Page.of(
                this.cursoInfoMapper.toInfoList(cursoPage.content()),
                cursoPage.page(),
                cursoPage.size(),
                cursoPage.totalElements()
            )
        );
    }

    public static final record Query(Filter filter, PageRequest pageRequest){}
    public static final record Result(Page<CursoInfo> cursosPage){}
}
