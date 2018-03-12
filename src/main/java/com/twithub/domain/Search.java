package com.twithub.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Search.
 */
@Entity
@Table(name = "search")
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "query")
    private String query;

    @Column(name = "jhi_timestamp")
    private Long timestamp;

    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public Search query(String query) {
        this.query = query;
        return this;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Search timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public Search userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Search search = (Search) o;
        if (search.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), search.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Search{" +
            "id=" + getId() +
            ", query='" + getQuery() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
