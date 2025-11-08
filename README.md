Desenvolvedores:
David Luiz Pereira Leite
Ian Anderson do Nascimento Melchor
Luiz Gustavo Araújo Marques
Maycon de Freitas Reis Silva

# Proffy Web

Interface web para conectar estudantes e professores particulares. Permite buscar professores por matéria/horário e entrar em contato para agendar aulas.

## Pré‑requisitos
- Node.js 18+ e npm (ou yarn/pnpm)
- API do Proffy em execução (defina a URL via variável de ambiente)

## Instalação e execução
```bash
# clone
git clone https://github.com/davidleite94/proffy_web
cd proffy_web

# dependências
npm install

# ambiente (ajuste a URL da API)
# copie .env.example para .env.local e defina, por exemplo:
# VITE_API_URL=https://sua-api.com
# ou REACT_APP_API_URL=https://sua-api.com

# modo desenvolvimento
npm run dev    # (ou npm start)

# build de produção
npm run build
npm run preview
```

## Estrutura (geral)
- `src/` componentes, páginas e estilos
- `public/` assets estáticos

## Scripts úteis
- `dev` – roda o app em desenvolvimento
- `build` – gera pacote de produção
- `preview` – serve o build localmente

## Licença
Este projeto é distribuído sob a licença MIT (salvo indicação em contrário no repositório).
