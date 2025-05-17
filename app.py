from sentence_transformers import SentenceTransformer, util
import json

# Load the knowledge base
with open("java_kb.json", "r") as f:
    kb = json.load(f)

# Load the model and encode the questions
model = SentenceTransformer("all-MiniLM-L6-v2")
kb_questions = [item["question"] for item in kb]
kb_embeddings = model.encode(kb_questions)

# Semantic search function
def find_best_match(query):
    query_embedding = model.encode(query)
    similarities = util.cos_sim(query_embedding, kb_embeddings)[0]
    best_idx = similarities.argmax().item()
    if similarities[best_idx] > 0.5:
        return kb[best_idx]["answer"]
    else:
        return "Sorry, I don't know the answer to that yet."

# Command-line chat loop
print("Java Knowledge Chatbot (type 'exit' to quit)")
while True:
    user_input = input("You: ")
    if user_input.lower() == "exit":
        break
    response = find_best_match(user_input)
    print("Bot:", response)
