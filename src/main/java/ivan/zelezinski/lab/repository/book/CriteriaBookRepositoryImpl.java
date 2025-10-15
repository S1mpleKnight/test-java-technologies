package ivan.zelezinski.lab.repository.book;

import ivan.zelezinski.lab.domain.*;
import ivan.zelezinski.lab.mapper.filter.BookFilterDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class CriteriaBookRepositoryImpl implements CriteriaBookRepository {

    private EntityManager entityManager;

    @Override
    public Page<Book> findAll(Pageable pageable, BookFilterDto filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);


        Fetch<Object, Object> fetch = root.fetch(Book_.BOOKCASE, JoinType.LEFT);
        fetch = fetch.fetch(Bookcase_.USER, JoinType.LEFT);


        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        Join<Book, Bookcase> bookcaseJoin = root.join(Book_.BOOKCASE, JoinType.LEFT);
        Join<Bookcase, User> userJoin = bookcaseJoin.join(Bookcase_.USER, JoinType.LEFT);

        if (Objects.nonNull(filter)) {
            if (Objects.nonNull(filter.getBookIds()) && !CollectionUtils.isEmpty(filter.getBookIds())) {
                predicates.add(root.get(Book_.UUID).in(filter.getBookIds()));
            }
            if (Objects.nonNull(filter.getBookcaseIds()) && !CollectionUtils.isEmpty(filter.getBookcaseIds())) {
                predicates.add(bookcaseJoin.get(Bookcase_.UUID).in(filter.getBookcaseIds()));
            }
            if (Objects.nonNull(filter.getUserIds()) && !CollectionUtils.isEmpty(filter.getUserIds())) {
                predicates.add(userJoin.get(User_.UUID).in(filter.getUserIds()));
            }
            if (Objects.nonNull(filter.getName())) {
                predicates.add(criteriaBuilder.equal(root.get(Book_.NAME), filter.getName()));
            }
            if (Objects.nonNull(filter.getFromReleaseDateTime())) {
                predicates.add(criteriaBuilder
                        .greaterThanOrEqualTo(root.get(Book_.RELEASE_DATE), filter.getFromReleaseDateTime()));
            }
            if (Objects.nonNull(filter.getToReleaseDateTime())) {
                predicates.add(criteriaBuilder
                        .lessThanOrEqualTo(root.get(Book_.RELEASE_DATE), filter.getToReleaseDateTime()));
            }
            if (Objects.nonNull(filter.getFromCreateDateTime())) {
                predicates.add(criteriaBuilder
                        .greaterThanOrEqualTo(root.get(Book_.CREATED_DATE), filter.getFromCreateDateTime()));
            }
            if (Objects.nonNull(filter.getToCreateDateTime())) {
                predicates.add(criteriaBuilder
                        .lessThanOrEqualTo(root.get(Book_.CREATED_DATE), filter.getToCreateDateTime()));
            }
            if (Objects.nonNull(filter.getFromUpdateDateTime())) {
                predicates.add(criteriaBuilder
                        .greaterThanOrEqualTo(root.get(Book_.UPDATED_DATE), filter.getFromUpdateDateTime()));
            }
            if (Objects.nonNull(filter.getToUpdateDateTime())) {
                predicates.add(criteriaBuilder
                        .greaterThanOrEqualTo(root.get(Book_.UPDATED_DATE), filter.getToUpdateDateTime()));
            }
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        List<Book> resultList = entityManager.createQuery(query).getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }
}
