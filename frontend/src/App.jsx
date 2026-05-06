import { useState } from 'react';

function App() {
  // Dados simulados (Mocks)
  const [saldo, setSaldo] = useState(1000.00);
  const [transacoes, setTransacoes] = useState([
    { id: 1, tipo: 'DEPOSITO', valor: 1500.00, data: '05/05/2026 14:30' },
    { id: 2, tipo: 'SAQUE', valor: 500.00, data: '06/05/2026 09:15' }
  ]);

  // --- CONTROLE DO MODAL ---
  const [modalAberto, setModalAberto] = useState(false);
  const [tipoTransacao, setTipoTransacao] = useState(''); // Guarda se é 'DEPOSITO' ou 'SAQUE'
  const [valorInput, setValorInput] = useState(''); // Guarda o valor digitado

  const abrirModal = (tipo) => {
    setTipoTransacao(tipo);
    setValorInput(''); // Limpa o campo sempre que o modal abre
    setModalAberto(true);
  };

  const fecharModal = () => {
    setModalAberto(false);
  };

  const handleConfirmar = (e) => {
    e.preventDefault(); // Evita que a página recarregue ao dar submit no formulário
    
    // Por enquanto, apenas exibimos no console. O próximo passo será enviar isso para o Java!
    console.log(`Simulando envio para a API: ${tipoTransacao} no valor de R$ ${valorInput}`);
    fecharModal();
  };

  return (
    <div className="min-h-screen bg-slate-100 p-6 font-sans text-slate-800">
      <div className="mx-auto max-w-2xl space-y-8">
        
        {/* Cabeçalho */}
        <header className="pt-8 pb-2">
          <h1 className="text-3xl font-bold text-slate-900">Simple Bank</h1>
          <p className="text-slate-500 mt-1">Bem-vindo, Venícius!</p>
        </header>

        {/* Card Principal - Saldo */}
        <section className="rounded-3xl bg-white p-8 shadow-sm border border-slate-200">
          <div className="space-y-2">
            <h2 className="text-sm font-bold text-slate-400 uppercase tracking-wider">Saldo Atual</h2>
            <p className="text-5xl font-extrabold text-slate-900">
              R$ {saldo.toFixed(2).replace('.', ',')}
            </p>
          </div>

          {/* Botões de Ação */}
          <div className="mt-8 flex gap-4">
            <button 
              onClick={() => abrirModal('DEPOSITO')}
              className="flex-1 rounded-2xl bg-green-600 py-4 text-sm font-bold text-white transition-colors hover:bg-green-700 active:scale-[0.98]"
            >
              DEPOSITAR
            </button>
            <button 
              onClick={() => abrirModal('SAQUE')}
              className="flex-1 rounded-2xl bg-red-600 py-4 text-sm font-bold text-white transition-colors hover:bg-red-700 active:scale-[0.98]"
            >
              SACAR
            </button>
          </div>
        </section>

        {/* Seção de Extrato */}
        <section className="space-y-4">
          <h3 className="text-lg font-bold text-slate-800">Extrato Recente</h3>
          
          <div className="rounded-3xl bg-white shadow-sm border border-slate-200 overflow-hidden">
            {transacoes.length === 0 ? (
              <p className="p-8 text-center text-slate-500">Nenhuma movimentação encontrada.</p>
            ) : (
              <ul className="divide-y divide-slate-100">
                {transacoes.map((t) => (
                  <li key={t.id} className="flex items-center justify-between p-6 hover:bg-slate-50 transition-colors">
                    <div className="flex flex-col gap-1">
                      <span className="font-bold text-slate-700">
                        {t.tipo === 'DEPOSITO' ? 'Depósito' : 'Saque'}
                      </span>
                      <span className="text-xs text-slate-400 font-medium">{t.data}</span>
                    </div>
                    <span className={`font-extrabold text-lg ${t.tipo === 'DEPOSITO' ? 'text-green-600' : 'text-red-600'}`}>
                      {t.tipo === 'DEPOSITO' ? '+ ' : '- '} 
                      R$ {t.valor.toFixed(2).replace('.', ',')}
                    </span>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </section>

      </div>

      {/* --- RENDERIZAÇÃO CONDICIONAL DO MODAL --- */}
      {modalAberto && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-slate-900/40 p-4 backdrop-blur-sm">
          <div className="w-full max-w-md rounded-3xl bg-white p-8 shadow-2xl">
            <h3 className="mb-6 text-2xl font-bold text-slate-900">
              {tipoTransacao === 'DEPOSITO' ? 'Realizar Depósito' : 'Fazer Saque'}
            </h3>

            <form onSubmit={handleConfirmar} className="space-y-6">
              <div>
                <label className="mb-2 block text-sm font-bold text-slate-500">
                  Valor (R$)
                </label>
                <input
                  type="number"
                  step="0.01"
                  min="0.01"
                  required
                  autoFocus
                  value={valorInput}
                  onChange={(e) => setValorInput(e.target.value)}
                  placeholder="0.00"
                  className="w-full rounded-xl border border-slate-200 bg-slate-50 p-4 text-2xl font-bold text-slate-900 outline-none transition-all focus:border-blue-500 focus:ring-4 focus:ring-blue-500/10"
                />
              </div>

              <div className="flex gap-4 pt-2">
                <button
                  type="button"
                  onClick={fecharModal}
                  className="flex-1 rounded-xl bg-slate-100 py-4 font-bold text-slate-600 transition-colors hover:bg-slate-200"
                >
                  Cancelar
                </button>
                <button
                  type="submit"
                  className={`flex-1 rounded-xl py-4 font-bold text-white transition-colors ${
                    tipoTransacao === 'DEPOSITO' 
                      ? 'bg-green-600 hover:bg-green-700' 
                      : 'bg-red-600 hover:bg-red-700'
                  }`}
                >
                  Confirmar
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;