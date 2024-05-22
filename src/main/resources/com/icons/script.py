import os
from PIL import Image

def make_black_pixels_transparent(input_path, output_path):
    # Apri l'immagine
    img = Image.open(input_path).convert("RGBA")
    
    # Ottieni i dati dei pixel
    data = img.getdata()
    
    new_data = []
    for item in data:
        # Cambia i pixel neri in trasparenti (R, G, B, A)
        if item[:3] == (255, 255, 255):
            new_data.append((0, 0, 0, 0))
        else:
            new_data.append(item)
    
    # Aggiorna l'immagine con i nuovi dati
    img.putdata(new_data)
    
    # Salva l'immagine
    img.save(output_path)

def process_all_images_in_directory(base_dir):
    for root, _, files in os.walk(base_dir):
        for file in files:
            if file.lower().endswith('.png'):
                input_path = os.path.join(root, file)
                output_path = os.path.join(root, 'processed_' + file)
                make_black_pixels_transparent(input_path, output_path)
                print(f'Processed {input_path} and saved to {output_path}')

# Esempio di utilizzo
process_all_images_in_directory('./dices')