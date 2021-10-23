package com.alkemy.icoons.icons.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "pais")
@Getter
@Setter
@SQLDelete(sql= "UPDATE pais SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class PaisEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String imagen;
	
	@NotBlank
	private String denominacion;
	
	@Column(name = "cant_habitantes")
	@NotNull
	private Long cantidadHabitantes;
	
	@NotNull
	private Long superficie;
	
	private boolean deleted = Boolean.FALSE;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "continente_id", insertable = false, updatable = false)
	private ContinenteEntity continente;
	
	@Column(name = "continente_id", nullable = false)
	private Long continenteId;
	
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(
			name = "icon_pais",
			joinColumns = @JoinColumn(name = "pais_id"),
			inverseJoinColumns = @JoinColumn(name = "icon_id"))
	private Set<IconEntity> icons = new HashSet<>();
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final PaisEntity other = (PaisEntity)obj;
		return other.id == this.id;
	}
	
}
