CREATE TABLE IF NOT EXISTS conta(
    id SERIAL PRIMARY KEY,
    nome_titular VARCHAR(150) NOT NULL,
    numero_conta VARCHAR(20) NOT NULL UNIQUE,
    saldo NUMERIC(15, 2) NOT NULL DEFAULT 0.00,
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVA',
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_conta_saldo_nao_negativo
        CHECK (saldo >= 0),

    CONSTRAINT chk_conta_status
        CHECK (status IN ('ATIVA', 'INATIVA'))
);

CREATE TABLE IF NOT EXISTS movimentacao(
    id SERIAL PRIMARY KEY,
    conta_origem_id INTEGER NOT NULL,
    conta_destino_id INTEGER NOT NULL,
    valor NUMERIC(15, 2) NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacao VARCHAR(255),

    CONSTRAINT fk_movimentacao_conta_origem
        FOREIGN KEY (conta_origem_id)
        REFERENCES conta(id),

    CONSTRAINT fk_movimentacao_conta_destino
        FOREIGN KEY (conta_destino_id)
        REFERENCES conta(id),

    CONSTRAINT chk_movimentacao_valor_positivo
        CHECK (valor > 0),

    CONSTRAINT chk_movimentacao_tipo
        CHECK (tipo IN ('TRANSFERENCIA'))
);

INSERT INTO conta (
    nome_titular,
    numero_conta,
    saldo,
    status
)
VALUES
('Maria Silva', '1001', 1000.00, 'ATIVA'),
('João Souza', '1002', 500.00, 'ATIVA'),
('Carlos Oliveira', '1003', 250.00, 'ATIVA'),
('Conta Inativa', '9999', 300.00, 'INATIVA');