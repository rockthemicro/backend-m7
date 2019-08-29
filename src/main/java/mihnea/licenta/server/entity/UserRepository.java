package mihnea.licenta.server.entity;

import org.springframework.data.repository.CrudRepository;

/* CrudRepository este o interfata care declara metode pentru maparea obiectelor la anumite key
 * In cazul de fata, datorita parametrizarii CrudRepository cu <User, String>, obiectele vor fi
 * de tipul User, iar keyle acestor obiecte vor fi de tipul String
 */
public interface UserRepository extends CrudRepository<User, String> {
}
