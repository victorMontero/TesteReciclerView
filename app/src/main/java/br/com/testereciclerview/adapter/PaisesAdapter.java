package br.com.testereciclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.testereciclerview.R;
import br.com.testereciclerview.interfaces.PaisesListener;
import br.com.testereciclerview.model.Pais;

public class PaisesAdapter extends RecyclerView.Adapter<PaisesAdapter.ViewHolder> {

    private List<Pais> listaPaises;
    private PaisesListener paisesListener;

    public PaisesAdapter(List<Pais>listaPaises, PaisesListener paisesListener){
        this.listaPaises = listaPaises;
        this.paisesListener = paisesListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pais_celula, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Pais pais = listaPaises.get(i);
        viewHolder.bindPais(pais);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paisesListener.onPaisClicado(pais);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView bandeiraImageView;
        private TextView nomeTextView;
        private TextView populacaoTextView;
        private TextView idiomaTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bandeiraImageView = itemView.findViewById(R.id.bandeira_image_view);
            nomeTextView = itemView.findViewById(R.id.nome_pais_text_view);
            populacaoTextView = itemView.findViewById(R.id.populacao_text_view);
            idiomaTextView = itemView.findViewById(R.id.idioma_text_view);
        }

        public void bindPais(Pais pais){
            nomeTextView.setText(pais.getNome());
            populacaoTextView.setText("POP: " +pais.getQuantidadeDeHabitantes());
            idiomaTextView.setText(pais.getIdioma());

            Picasso.get().load(pais.getBandeira()).into(bandeiraImageView);
        }

    }
}
