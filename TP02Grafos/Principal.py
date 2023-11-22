import numpy as np
from itertools import combinations
import os
import re

#Esse arquivo roda sobre todos os 'pmed' da pasta files.

def generate_matrix_from_file(file_name):
    with open(file_name, 'r') as file:
        lines = file.readlines()

    num_vertices, num_edges, centros = map(int, lines[0].split())

    matrix = np.full((num_vertices, num_vertices), np.inf)
    np.fill_diagonal(matrix, 0)

    for line in lines[1:]:
        i, j, cost = map(int, line.split()[:3])
        matrix[i-1][j-1] = cost
        matrix[j-1][i-1] = cost

    for k in range(num_vertices):
        for i in range(num_vertices):
            for j in range(num_vertices):
                matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j])

    return matrix, centros

def k_center_aproximado(distance_matrix, k):
    num_vertices = distance_matrix.shape[0]
    num_centers = min(k, num_vertices)

    # Initialize the list of centers with the first vertex
    centers = [0]

    # Initialize the array to store the distances from vertices to centers
    distances = np.full(num_vertices, np.inf)
    distances[0] = 0

    # Find the remaining k-1 centers
    for _ in range(1, num_centers):
        max_distance = 0
        max_index = 0

        # Update distances to the nearest center for each vertex
        for vertex in range(num_vertices):
            min_distance = np.inf
            for center in centers:
                min_distance = min(min_distance, distance_matrix[vertex, center])
            distances[vertex] = min_distance

            # Track the vertex with the maximum distance to the nearest center
            if min_distance > max_distance:
                max_distance = min_distance
                max_index = vertex

        # Add the vertex with the maximum distance as a new center
        centers.append(max_index)

    # Calculate the radius of the solution
    radius = np.max(distances)
    return centers, radius

def k_center_brute_force(matrix, K):
    num_vertices = matrix.shape[0]
    best_centers = None
    best_radius = np.inf

    # Generate all possible combinations of K vertices
    combos = combinations(range(num_vertices), K)

    for combo in combos:
        max_dist = 0

        # Calculate the maximum distance for each combination
        for i in range(num_vertices):
            dist = np.min(matrix[i, combo])
            max_dist = max(max_dist, dist)

        # Update the best solution if the current combination has a smaller radius
        if max_dist < best_radius:
            best_centers = combo
            best_radius = max_dist

    return best_centers, best_radius

folder_path = "Files"  # Caminho para a pasta contendo os arquivos

# Obtém a lista de arquivos na pasta
# file_list = os.listdir(folder_path)
file_list = sorted(os.listdir(folder_path), key=lambda x: int(re.sub('[^0-9]', '', x)))
print(file_list)
output_file_name = "resultado.txt"
# Itera sobre cada arquivo na lista
with open(output_file_name, 'w') as output_file:
    for file_name in file_list:
        # Constrói o caminho completo do arquivo
        file_path = os.path.join(folder_path, file_name)

        # Executa o código para o arquivo atual
        matrix, centros = generate_matrix_from_file(file_path)

        # bolota, raios = k_center_aproximado(matrix, centros)
        bolota, raios = k_center_brute_force(matrix, centros)

        # Escreve o valor de raios no arquivo, separando por quebra de linha
        output_file.write(f"{raios}\n")

        print()
    

