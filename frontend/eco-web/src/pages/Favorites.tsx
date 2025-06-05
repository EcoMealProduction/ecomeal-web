
import React, { useState } from 'react';
import { Heart, MapPin } from 'lucide-react';
import { FoodCard } from '@/components/FoodCard';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';

const mockFavoriteOffers = [
  {
    id: '1',
    title: 'Assorted Pastries & Fresh Bread',
    restaurant: 'Bakery Dulce Vita',
    originalPrice: 85,
    discountedPrice: 25,
    distance: '0.3km',
    timeLeft: '2h left',
    image: 'photo-1618160702438-9b02ab6515c9',
    category: 'Bakery',
    pickupTime: 'Today 18:00-20:00'
  }
];

const Favorites = () => {
  const [favorites, setFavorites] = useState<Set<string>>(new Set(['1']));

  const handleReserve = (id: string) => {
    console.log('Reserving offer:', id);
  };

  const handleToggleFavorite = (id: string) => {
    const newFavorites = new Set(favorites);
    if (newFavorites.has(id)) {
      newFavorites.delete(id);
    } else {
      newFavorites.add(id);
    }
    setFavorites(newFavorites);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-white p-4 pt-8 border-b">
        <h1 className="text-2xl font-bold text-gray-900 mb-2">Saved Items</h1>
        <p className="text-gray-600">Your favorite food offers</p>
      </div>

      <div className="p-4">
        {favorites.size > 0 ? (
          <div className="space-y-4">
            {mockFavoriteOffers.map((offer) => (
              <FoodCard
                key={offer.id}
                offer={offer}
                onReserve={handleReserve}
                onToggleFavorite={handleToggleFavorite}
                isFavorite={favorites.has(offer.id)}
              />
            ))}
          </div>
        ) : (
          <Card className="mt-8">
            <CardContent className="p-8 text-center">
              <Heart size={48} className="mx-auto text-gray-300 mb-4" />
              <h3 className="text-lg font-medium text-gray-900 mb-2">No Saved Items Yet</h3>
              <p className="text-gray-500 mb-4">
                Start exploring and save your favorite food offers for easy access later.
              </p>
              <Button className="bg-green-600 hover:bg-green-700">
                Discover Food Offers
              </Button>
            </CardContent>
          </Card>
        )}
      </div>
    </div>
  );
};

export default Favorites;
