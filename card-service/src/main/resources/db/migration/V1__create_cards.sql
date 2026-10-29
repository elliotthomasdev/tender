CREATE TABLE cards (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    balance numeric(12,2) NOT NULL CONSTRAINT cards_balance_non_negative CHECK (balance >= 0),
    currency char(3) NOT NULL,
    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz NOT NULL DEFAULT now()
);
