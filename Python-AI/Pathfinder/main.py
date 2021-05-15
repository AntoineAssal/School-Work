# -------------------------------------------------------
# Assignment 1
# Written by Antoine Assal 40022745
# For COMP 472 AA – Summer 2021
# --------------------------------------------------------
# Setup and import statements
import pygame, sys, numpy

pygame.init()
W = 800
H = 600
# Color Palette in RGB (red, blue, green)
AMAZON_BG = (103, 145, 120)                                                             # HEX : 679178
MAXIMUM_YELLOW_RED = (255, 186, 73)                                                     # HEX : FFBA49
BLACK = (0,0,9)                                                                         # HEX : 000009
# Set display's resolution, title, background and icon
screen = pygame.display.set_mode((W,H))
icon = pygame.image.load('conu.png')
pygame.display.set_caption("COMP 472 - Assignment 1 by Antoine Assal")
pygame.display.set_icon(icon)
# Icons/emojis for places
quarantine_img = pygame.image.load('virus.png')
vaccine_img = pygame.image.load('vaccine.png')
playground_img = pygame.image.load('park.png')
# Board variables
cell_width = 0.1
cell_length = 0.2
def add_quarantine_place():
    screen.blit(quarantine_img, (400,400))
#def add_vaccine_spot(x):


#def add_playing_ground(x):

# Settings Page
def settings():
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
        screen.blit()
# Main Loop
while True:
    for event in pygame.event.get():
        if event.type==pygame.QUIT:
            print("Program terminated successfully, have a good day!")
            sys.exit()
    screen.fill(AMAZON_BG)
    add_quarantine_place()
    pygame.display.update()
    settings()
