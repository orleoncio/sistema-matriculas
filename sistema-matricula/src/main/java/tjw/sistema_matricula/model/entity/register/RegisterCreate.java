package tjw.sistema_matricula.model.entity.register;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Classe base abstrata que contém os campos de auditoria (quem criou e quando).
 * As entidades que herdarem desta classe terão automaticamente esses campos.
 * Renomeada de AuditableEntity para RegisterCreate.
 */
@MappedSuperclass // Indica que os campos devem ser mapeados nas tabelas filhas.
@EntityListeners(AuditingEntityListener.class) // Habilita o preenchimento automático.
public abstract class RegisterCreate {

    @CreatedDate
    // Define o nome da coluna no banco e garante que não seja alterado após a criação
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @CreatedBy
    @Column(name = "criado_por", length = 100, updatable = false)
    private String criadoPor;

    // --- GETTERS E SETTERS ---

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }
}