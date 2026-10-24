package com.veyro.model;

import com.veyro.enums.Method;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"project_identifier", "method", "path"}))
public class Route {

    public Route() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Method method;

    @Column(nullable = false)
    private String path;

    private int status;

    @JdbcTypeCode(SqlTypes.JSON)
    private String body;

    @Column(nullable = false)
    private String projectIdentifier;

    public int getId() {
        return id;
    }

    public Method getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
