package com.example.cadastro.Services;

import com.unicesumar.cadastroprodutos.entity.Produto;
import com.unicesumar.cadastroprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos (GET)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Cadastrar novo produto (POST)
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualizar produto (PUT)
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());
        produto.setStatus(produtoAtualizado.getStatus());

        return produtoRepository.save(produto);
    }

    // Remover produto (DELETE)
    public void remover(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
