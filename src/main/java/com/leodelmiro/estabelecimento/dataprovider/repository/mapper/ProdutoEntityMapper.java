package com.leodelmiro.estabelecimento.dataprovider.repository.mapper;

import com.leodelmiro.estabelecimento.core.domain.Imagem;
import com.leodelmiro.estabelecimento.core.domain.Produto;
import com.leodelmiro.estabelecimento.dataprovider.repository.entity.ImagemEntity;
import com.leodelmiro.estabelecimento.dataprovider.repository.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProdutoEntityMapper {
    @Mapping(source = "imagens", target = "imagens")
    ProdutoEntity toProdutoEntity(Produto produto);

    @Mapping(source = "imagens", target = "imagens")
    Produto toProduto(ProdutoEntity produtoEntity);

    default ImagemEntity map(Imagem imagem) {
        if (imagem == null) {
            return null;
        }
        ImagemEntity imagemEntity = new ImagemEntity();
        imagemEntity.setId(imagem.getId());
        imagemEntity.setNome(imagem.getNome());
        imagemEntity.setUrl(imagem.getUrl());
        imagemEntity.setCriadoEm(imagem.getCriadoEm());
        return imagemEntity;
    }

    default Set<ImagemEntity> map(Set<Imagem> imagens) {
        if (imagens == null) {
            return null;
        }
        return imagens.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    default Imagem map(ImagemEntity imagemEntity) {
        if (imagemEntity == null) {
            return null;
        }
        Imagem imagem = new Imagem();
        imagem.setId(imagemEntity.getId());
        imagem.setNome(imagemEntity.getNome());
        imagem.setUrl(imagemEntity.getUrl());
        imagem.setCriadoEm(imagemEntity.getCriadoEm());
        return imagem;
    }

    default Set<Imagem> mapToImagemSet(Set<ImagemEntity> imagemEntities) {
        if (imagemEntities == null) {
            return null;
        }
        return imagemEntities.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

}
